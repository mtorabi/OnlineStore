package com.razanpardazesh.onlinestore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.razanpardazesh.mtglibrary.tools.AsyncWrapper;
import com.razanpardazesh.mtglibrary.tools.NetworkAsyncWrapper;
import com.razanpardazesh.onlinestore.Tools.FabWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.Tools.ToolbarWrapper;
import com.razanpardazesh.onlinestore.ViewAdapter.ProductsGroupsAdapter;
import com.razanpardazesh.onlinestore.ViewAdapter.decorations.DividerDecoration;
import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsFakeRepo;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsServerRepo;

import java.util.ArrayList;

public class ProductsGroupsActivity extends AppCompatActivity {

    private static final String EXTRA_KEY_GROUP_ID = "groupID";
    private static final String EXTRA_KEY_GROUP_TITLE = "groupTitle";

    private final int KEY_LIST_COUNT = 20;

    private ProductGroupAnswer productsGroupAnswer;
    private IProductsGroups productsGroupsRepo;
    private AsyncWrapper getProducsGroupsAsync;
    private ProductsGroupsAdapter groupsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_groups);


        parseIntent(getIntent());
        initToolbar();
        initFloatActionButton();
        getProductsGroupsData();

    }

    private void parseIntent(Intent data) {
        productsGroupAnswer = new ProductGroupAnswer();
        ProductsGroup group = new ProductsGroup();
        productsGroupAnswer.setGroup(group);

        if (data == null)
            return;

        if (!data.hasExtra(EXTRA_KEY_GROUP_ID))
            return;

        group.setId(data.getLongExtra(EXTRA_KEY_GROUP_ID, 0));
        group.setName(data.getStringExtra(EXTRA_KEY_GROUP_TITLE));
    }

    public static void openActivity(FragmentActivity act, Long gruopId, String groupTitle) {
        Intent data = new Intent(act, ProductsGroupsActivity.class);
        data.putExtra(EXTRA_KEY_GROUP_ID, gruopId);
        data.putExtra(EXTRA_KEY_GROUP_TITLE, groupTitle);
        act.startActivity(data);
    }

    private Boolean getProductsGroupsData() {
        if (productsGroupAnswer == null || productsGroupAnswer.getGroup() == null || productsGroupAnswer.getGroup().getId() < 0)
            return false;

        if (SessionManagement.getInstance(getApplicationContext()).getFakeBind()) {
            productsGroupsRepo = new ProductsGroupsFakeRepo();
        } else {
            productsGroupsRepo = new ProductsGroupsServerRepo();
        }

        getProducsGroupsAsync = new NetworkAsyncWrapper().initDefaultProgressDialog("", true).setDoOnBackground(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                return productsGroupsRepo.getGroups(getApplicationContext(), productsGroupAnswer.getGroup().getId(), "", productsGroupAnswer.getLastIndex(), KEY_LIST_COUNT);
            }
        }).setDoOnAnswer(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                if (object == null && !(object instanceof ProductGroupAnswer))
                    return null;

                ProductGroupAnswer answer = (ProductGroupAnswer) object;

                if (answer.getIsSuccess() == 1 && answer.getGroup() != null)
                    setProductsGroupData(answer);

                return null;
            }
        }).setDoOnError(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                if (object instanceof Throwable)
                    Log.e("Razan", "call: ", (Throwable) object);
                return null;
            }
        });
        getProducsGroupsAsync.run(getApplicationContext());
        return true;
    }

    private void setProductsGroupData(ProductGroupAnswer group) {
        this.productsGroupAnswer = group;
        initRecycleView();
    }

    private void initRecycleView() {

        if (productsGroupAnswer == null || productsGroupAnswer.getGroup() == null) {
            return;
        }

        RecyclerView row_groups = (RecyclerView) findViewById(R.id.row_groups);

        RecyclerView.LayoutManager rowManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        row_groups.setLayoutManager(rowManager);

        DividerDecoration decoration = new DividerDecoration(ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider_basket_item));
        row_groups.addItemDecoration(decoration);

        groupsAdapter = new ProductsGroupsAdapter(this);

        groupsAdapter.addItem(productsGroupAnswer.getGroup().getSubGroups());

        row_groups.setAdapter(groupsAdapter);

    }

    private void initToolbar() {
        ToolbarWrapper toolbarWrapper = new ToolbarWrapper(this);

        String title = getTitle().toString();

        if (productsGroupAnswer != null && productsGroupAnswer.getGroup() != null && !TextUtils.isEmpty(productsGroupAnswer.getGroup().getName()))
            title = productsGroupAnswer.getGroup().getName();

        toolbarWrapper.initToolbarWithBack(R.id.toolbar, title, null);

    }

    private void initFloatActionButton() {
        FabWrapper fabWrapper = new FabWrapper(this, false);
        fabWrapper.initFab(R.id.fab, null);

    }


}

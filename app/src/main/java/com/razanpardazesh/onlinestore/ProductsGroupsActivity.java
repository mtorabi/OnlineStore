package com.razanpardazesh.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.razanpardazesh.mtglibrary.CustomView.DialogBuilder;
import com.razanpardazesh.mtglibrary.CustomView.recycler.IRecyclerRow;
import com.razanpardazesh.mtglibrary.CustomView.recycler.MTGRecyclerView;
import com.razanpardazesh.mtglibrary.CustomView.recycler.MTGViewHolder;
import com.razanpardazesh.mtglibrary.CustomView.recycler.OnRecycleViewListener;
import com.razanpardazesh.mtglibrary.tools.AsyncWrapper;
import com.razanpardazesh.mtglibrary.tools.NetworkAsyncWrapper;
import com.razanpardazesh.onlinestore.Tools.FabWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.Tools.ToolbarWrapper;
import com.razanpardazesh.onlinestore.ViewAdapter.ProductsGroupsAdapter;
import com.razanpardazesh.onlinestore.ViewAdapter.decorations.DividerDecoration;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsFakeRepo;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsServerRepo;

import java.util.ArrayList;
import java.util.List;

public class ProductsGroupsActivity extends AppCompatActivity {

    private static final String EXTRA_KEY_GROUP_ID = "groupID";
    private static final String EXTRA_KEY_GROUP_TITLE = "groupTitle";

    private final int KEY_LIST_COUNT = 20;

    private ProductGroupAnswer productsGroupAnswer;
    private IProductsGroups productsGroupsRepo;
    private NetworkAsyncWrapper getProducsGroupsAsync;
    private MTGRecyclerView row_groups = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_groups);


        parseIntent(getIntent());
        initToolbar();
        initFloatActionButton();
        getProductsGroupsData();
        initRecycleView();

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

        if (getProducsGroupsAsync == null)
            getProducsGroupsAsync = new NetworkAsyncWrapper();

        getProducsGroupsAsync.setDoOnBackground(new AsyncWrapper.Callback() {
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
                if (object instanceof Throwable) {
                    DialogBuilder dialog = new DialogBuilder();
                    dialog.showAlert(ProductsGroupsActivity.this, (Throwable) object);
                }
                return null;
            }
        });
        getProducsGroupsAsync.run(getApplicationContext());
        return true;
    }

    private void setProductsGroupData(ProductGroupAnswer group) {
        this.productsGroupAnswer = group;
        addRow(group);
    }

    private void initRecycleView() {

        if (productsGroupAnswer == null || productsGroupAnswer.getGroup() == null) {
            return;
        }

        if (row_groups == null) {
            row_groups = (MTGRecyclerView) findViewById(R.id.row_groups);
        }


        row_groups.setDefaultDivider().setDefaultAdapter(new OnRecycleViewListener() {
            @Override
            public MTGViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (parent == null || parent.getContext() == null)
                    return null;
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_group_item, parent, false);
                MTGViewHolder holder = new MTGViewHolder(view);
                holder.defineChild(R.id.imgGroup);
                holder.defineChild(R.id.txtGroupName);
                return holder;
            }

            @Override
            public void onBindViewHolder(MTGViewHolder holder, int position, IRecyclerRow row) {
                if (row instanceof ProductsGroup) {
                    ProductsGroup group = (ProductsGroup) row;

                    holder.getTextView(R.id.txtGroupName).setText(group.getName());
                    if (SessionManagement.getInstance(getApplicationContext()).getFakeBind())
                        holder.getImageView(R.id.imgGroup).setImageResource(Integer.parseInt(group.getImage(getApplicationContext())));
                    else {
                        //TODO MTG
                    }
                    return;
                }

                if (row instanceof ProductSummary)
                {
                    ProductSummary product = (ProductSummary) row;

                    holder.getTextView(R.id.txtGroupName).setText(product.getName());
                    if (SessionManagement.getInstance(getApplicationContext()).getFakeBind())
                        holder.getImageView(R.id.imgGroup).setImageResource(Integer.parseInt(product.getImage(getApplicationContext())));
                    else {
                        //TODO MTG
                    }
                    return;
                }
            }

            @Override
            public void onLoadMore() {
                if (productsGroupAnswer == null || productsGroupAnswer.getGroup() == null || productsGroupAnswer.getGroup().getId() < 0 || productsGroupAnswer.getHasMore() != 1) {
                    return;
                }
                getProductsGroupsData();
            }

            @Override
            public void onRowClick(IRecyclerRow row, int pos, View v) {
                if (row instanceof ProductsGroup) {
                    ProductsGroup group = (ProductsGroup) row;

                    ProductsGroupsActivity.openActivity(ProductsGroupsActivity.this, group.getId(), group.getName());
                    return;
                }

                if (row instanceof ProductSummary) {
                    ProductSummary product = (ProductSummary) row;

                    ProductActivity.openActivity(ProductsGroupsActivity.this, product.getId());
                    return;
                }
            }

            @Override
            public Boolean onRowLongClick(IRecyclerRow row, int pos, View v) {
                return null;
            }
        });

    }

    private void addRow(ProductGroupAnswer productsGroupAnswer) {
        if (row_groups == null) {
            initRecycleView();
        }
        if (productsGroupAnswer.getGroup().getSubGroups() != null && productsGroupAnswer.getGroup().getSubGroups().size() != 0)
            row_groups.addRows(productsGroupAnswer.getGroup().getSubGroups(), productsGroupAnswer.getHasMore() != 1);
        else if(productsGroupAnswer.getGroup().getSubProducts() != null && productsGroupAnswer.getGroup().getSubProducts().size() != 0)
            row_groups.addRows(productsGroupAnswer.getGroup().getSubProducts(), productsGroupAnswer.getHasMore() != 1);

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

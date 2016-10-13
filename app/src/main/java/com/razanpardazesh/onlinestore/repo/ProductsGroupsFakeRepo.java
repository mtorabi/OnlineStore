package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductsGroupsFakeRepo implements IProductsGroups {
    private ArrayList<ProductsGroup> groups;

    public ArrayList<ProductsGroup> getGroups() {

        if (groups == null || groups.size() == 0) {

            groups = new ArrayList<>();

            int imageRes = 0;
            String name = "";


            for (int i = 0; i < 9; i++) {
                switch (i) {
                    case 8:
                        imageRes = R.drawable.c_chai_limo;
                        name = "گروه چای";
                        break;

                    case 7:
                        imageRes = R.drawable.c_cooki_1;
                        name = "گروه کلوچه";

                        break;


                    case 6:
                        imageRes = R.drawable.c_cooki_2;
                        name = "گروه روغن";

                        break;

                    case 5:
                        imageRes = R.drawable.c_chai_sonati;
                        name = "گروه خوراکی";

                        break;

                    case 4:
                        imageRes = R.drawable.c_cooki_3;
                        name = "گروه نوشیدنی ";

                        break;

                    case 3:
                        imageRes = R.drawable.c_cooki_3;
                        name = "گروه چای";

                        break;

                    case 2:
                        imageRes = R.drawable.c_cooki_4;
                        name = "گروه نان";

                        break;

                    case 1:
                        imageRes = R.drawable.c_chai_vije;
                        name = "گروه الماس";

                        break;

                    case 0:
                        imageRes = R.drawable.c_chaie_babone;
                        name = "گروه بهداشتی";

                        break;
                }

                ProductsGroup group = new ProductsGroup();
                group.setId((long) imageRes);
                group.setName(name);

                groups.add(group);

            }
        }
        return groups;
    }

    @Override
    public ProductGroupAnswer getGroups(Context context,long groupId, String key, long startIndex,
                                        int count) {

        ProductGroupAnswer answer = new ProductGroupAnswer();
        answer.setIsSuccess(1);
        answer.setHasMore(0);
        ArrayList<ProductsGroup> productsGroups = new ArrayList<>();
        ProductsGroup group = new ProductsGroup();
        group.setId(groupId);

        for (int i = 0; i < getGroups().size(); i++) {
            productsGroups.add(getGroups().get(i));

        }

        group.setSubGroups(productsGroups);

        answer.setGroup(group);

        answer.setIsSuccess(1);

        return answer;
    }
}
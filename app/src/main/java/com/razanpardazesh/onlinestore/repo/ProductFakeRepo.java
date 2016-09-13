package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductListAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProducts;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductFakeRepo implements IProducts {

    private ArrayList<Product> products;


    public ArrayList<Product> getProducts() {
        if (products == null || products.size() == 0) {
            products = new ArrayList<>();

            int imageRes = 0;
            String title = "";
            float price = 0f;

            for (int i = 0; i < 9; i++) {

                switch (i) {
                    case 8:
                        imageRes = R.drawable.c_chai_limo;
                        title = "چای لیمویی بسته متوسط";
                        price = 20000;
                        break;

                    case 7:
                        imageRes = R.drawable.c_cooki_1;
                        title = "کلوچه ی نارگیلی دوتایی";
                        price = 2000;
                        break;
                    case 6:
                        imageRes = R.drawable.c_chai_zaferani;
                        title = "چای زعفرانی بسته متوسط";
                        price = 30000;
                        break;
                    case 5:
                        imageRes = R.drawable.c_cooki_2;
                        title = "کلوچه نارگیلی 8 تایی";
                        price = 12000;
                        break;
                    case 4:
                        imageRes = R.drawable.c_chai_sonati;
                        title = "چایی سنتی بسته متوسط";
                        price = 20000;
                        break;
                    case 3:
                        imageRes = R.drawable.c_cooki_3;
                        title = "کلوچه نارگیلی 20 تایی";
                        price = 2000;
                        break;
                    case 2:
                        imageRes = R.drawable.c_cooki_4;
                        title = "کلوچه نارگیلی 12 تایی";
                        price = 30000;
                        break;

                    case 1:
                        imageRes = R.drawable.c_chai_vije;
                        title = "چای ویژه";
                        price = 8000;
                        break;
                    case 0:
                        imageRes = R.drawable.c_chaie_babone;
                        title = "چای بابونه بسته متوسط";
                        price = 10000;
                        break;
                }

                Product product = new Product();

                product.setName(title);
                product.setId(imageRes);
                product.setPrice(price);

                products.add(product);
            }
        }
        return products;
    }

    @Override
    public ProductListAnswer getProducts(Context context, String key, long lastIndex, int Count) {
        return null;
    }

    @Override
    public ProductListAnswer getGroupsProducts(Context context, String key, long lastIndex, int Count, long groupID) {
        return null;
    }

    @Override
    public ProductListAnswer getBrandsProducts(Context context, String key, long lastIndex, int Count, long brandID) {
        return null;
    }

    @Override
    public ProductListAnswer getMostVistedProducts(Context context, String key, long lastIndex, int Count) {

        ProductListAnswer answer = new ProductListAnswer();

        answer.setIsSuccess(1);
        answer.setHasMore(0);
        ArrayList<ProductSummary> summaries = new ArrayList<>();
        answer.setProducts(summaries);

        for (int i = 0; i < getProducts().size(); i++) {
            answer.getProducts().add(getProducts().get(i));
        }


        return answer;
    }

    @Override
    public ProductListAnswer getMostSoldProducts(Context context, String key, long lastIndex, int Count) {
        ProductListAnswer answer = new ProductListAnswer();

        answer.setIsSuccess(1);
        answer.setHasMore(0);
        ArrayList<ProductSummary> summaries = new ArrayList<>();
        answer.setProducts(summaries);

        for (int i = getProducts().size() - 1; i >= 0; i--) {
            answer.getProducts().add(getProducts().get(i));
        }

        return answer;
    }

    @Override
    public ProductAnswer getProduct(Context context, long id) {
        return null;
    }
}

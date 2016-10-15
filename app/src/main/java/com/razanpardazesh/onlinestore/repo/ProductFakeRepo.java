package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.data.Content;
import com.razanpardazesh.onlinestore.data.ContentImage;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductListAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProducts;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductFakeRepo implements IProducts {

    private ArrayList<ProductSummary> products;
    private ArrayList<ContentImage> images;

    public ArrayList<ProductSummary> getProducts() {

        if (images == null || images.size()== 0)
        {
            images = new ArrayList<>();

            images.add(new ContentImage(R.drawable.c_chai_limo));
            images.add(new ContentImage(R.drawable.c_cooki_1));
            images.add(new ContentImage(R.drawable.c_chai_zaferani));
            images.add(new ContentImage(R.drawable.c_cooki_2));
            images.add(new ContentImage(R.drawable.c_chai_sonati));
            images.add(new ContentImage(R.drawable.c_cooki_3));
            images.add(new ContentImage(R.drawable.c_cooki_4));
            images.add(new ContentImage(R.drawable.c_chai_vije));
            images.add(new ContentImage(R.drawable.c_chaie_babone));
        }

        if (products == null || products.size() == 0) {
            products = new ArrayList<>();

            int imageRes = 0;
            String title = "";
            float price = 0f;
            String desccription = "";

            for (int i = 0; i < 9; i++) {

                switch (i) {
                    case 8:
                        imageRes = R.drawable.c_chai_limo;
                        title = "چای لیمویی بسته متوسط";
                        price = 20000;
                        desccription = "این چای با طعم لیمو در بسته بندی بسیار شیک و مناسب استفاده در بعد از ظهرهای داغ تابستان است. طعم طبیعی لیمو با بهترین کیفیت برگه های چای ارمغان شرکت رفاه است به تمامی دوستداران طعم چای طبیعی ایرانی.";
                        break;

                    case 7:
                        imageRes = R.drawable.c_cooki_1;
                        title = "کلوچه ی نارگیلی دوتایی";
                        price = 2000;
                        desccription = "کلوچه نارگیلی با استفاده از بهترین مواد اولیه و در محیطی کاملا بهداشتی تولید شده و طعم طبخ ایرانی - لاهیجانی ان باعث شده این محصول یکی از پرطرفدارتین محصولات این شرکت در میان مصرف کنندگان باشد.";
                        break;
                    case 6:
                        imageRes = R.drawable.c_chai_zaferani;
                        title = "چای زعفرانی بسته متوسط";
                        price = 30000;
                        desccription = "تجمیع خواص زعفران نشاط آور و چای فرح بخش گیلان کافیست تا عصر یک روز دل انگیز را زیباتر نبوده و خستگی یک روز کاری را از تن شما به در کند. کافیست این محصول را با دستور طبخ درج شده در پشت کالا تهیه نموده و میل نمایید.";
                        break;
                    case 5:
                        imageRes = R.drawable.c_cooki_2;
                        title = "کلوچه نارگیلی 8 تایی";
                        desccription = "کلوچه نارگیلی با استفاده از بهترین مواد اولیه و در محیطی کاملا بهداشتی تولید شده و طعم طبخ ایرانی - لاهیجانی ان باعث شده این محصول یکی از پرطرفدارتین محصولات این شرکت در میان مصرف کنندگان باشد.";
                        price = 12000;
                        break;
                    case 4:
                        imageRes = R.drawable.c_chai_sonati;
                        title = "چایی سنتی بسته متوسط";
                        price = 20000;
                        desccription = "چای چای است حتی اگر سنتی نباشد چه رسد به اینکه بتوان در یک محصول چای دستچین شده از میان تازه ترین برگ های چای موجود در باغ های سبز لاهیجان را تهیه نمود";
                        break;
                    case 3:
                        imageRes = R.drawable.c_cooki_3;
                        title = "کلوچه نارگیلی 20 تایی";
                        price = 2000;
                        desccription = "کلوچه نارگیلی با استفاده از بهترین مواد اولیه و در محیطی کاملا بهداشتی تولید شده و طعم طبخ ایرانی - لاهیجانی ان باعث شده این محصول یکی از پرطرفدارتین محصولات این شرکت در میان مصرف کنندگان باشد.";
                        break;
                    case 2:
                        imageRes = R.drawable.c_cooki_4;
                        title = "کلوچه نارگیلی 12 تایی";
                        price = 30000;
                        desccription = "کلوچه نارگیلی با استفاده از بهترین مواد اولیه و در محیطی کاملا بهداشتی تولید شده و طعم طبخ ایرانی - لاهیجانی ان باعث شده این محصول یکی از پرطرفدارتین محصولات این شرکت در میان مصرف کنندگان باشد.";
                        break;

                    case 1:
                        imageRes = R.drawable.c_chai_vije;
                        title = "چای ویژه";
                        price = 8000;
                        desccription = "چای چای است حتی اگر ویژه نباشد چه رسد به اینکه بتوان در یک محصول چای دستچین شده از میان تازه ترین برگ های چای موجود در باغ های سبز لاهیجان را تهیه نمود";
                        break;
                    case 0:
                        imageRes = R.drawable.c_chaie_babone;
                        title = "چای بابونه بسته متوسط";
                        price = 10000;
                        desccription = "تجمیع خواص بابونه و چای فرح بخش گیلان کافیست تا عصر یک روز دل انگیز را زیباتر نبوده و خستگی یک روز کاری را از تن شما به در کند. کافیست این محصول را با دستور طبخ درج شده در پشت کالا تهیه نموده و میل نمایید.";
                        break;
                }

                ProductSummary product = new ProductSummary();

                product.setName(title);
                product.setId(imageRes);
                product.setPrice(price);
                Content content = new Content();
                content.setImages(images);
                product.setContent(content);
                product.setDescription(desccription + " " + desccription + " " + desccription);

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
        ProductAnswer answer = new ProductAnswer();



        for (int i = 0; i < getProducts().size(); i++) {
            if (id == getProducts().get(i).getId())
            {
                answer.setIsSuccess(1);
                answer.setProduct(getProducts().get(i));
                return answer;
            }

        }
        answer.setIsSuccess(0);
        return answer;
    }
}

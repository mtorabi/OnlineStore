package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.data.ProductsGroup;

import org.json.JSONObject;

/**
 * Created by Torabi on 9/13/2016.
 */

public class ProductAnswer extends ServerAnswer {

    private final String KEY_PRODUCT = "p";
    private ProductSummary product;

    public ProductSummary getProduct() {
        return product;
    }

    public void setProduct(ProductSummary product) {
        this.product = product;
    }

    @Override
    public void fillByJson(JSONObject jsonObject) {
        super.fillByJson(jsonObject);

        if (jsonObject == null) {
            return;
        }

        if (jsonObject.has(KEY_PRODUCT)) {
            try {
                ProductSummary productSummary = new ProductSummary();
                productSummary.fillByJson(jsonObject.getJSONObject(KEY_PRODUCT));
                setProduct(productSummary);
            } catch (Exception e) {
                LogWrapper.loge("fillByJson: setProduct: ",e);
            }

        }
    }
}

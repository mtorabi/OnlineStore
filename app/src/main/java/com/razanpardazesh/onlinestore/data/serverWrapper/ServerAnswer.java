package com.razanpardazesh.onlinestore.data.serverWrapper;

/**
 * Created by Torabi on 9/13/2016.
 */

public abstract class ServerAnswer {

    private int isSuccess = 0;
    private int hasMore = 0;


    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }
}
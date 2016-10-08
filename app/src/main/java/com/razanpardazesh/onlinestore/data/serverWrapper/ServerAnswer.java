package com.razanpardazesh.onlinestore.data.serverWrapper;

/**
 * Created by Torabi on 9/13/2016.
 */

public abstract class ServerAnswer {

    private int isSuccess = 0;
    private int hasMore = 0;
    private long lastIndex =0;
    private String message = null;

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

    public long getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(long lastIndex) {
        this.lastIndex = lastIndex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Boolean isSuccess(Object answer)
    {
        if (answer == null)
            return false;

        if (!(answer instanceof ServerAnswer))
            return false;

        ServerAnswer obj = (ServerAnswer) answer;

        if (obj.getIsSuccess()!= 1)
            return false;

        return true;

    }

    public static Boolean hasMore(Object answer)
    {
        if (answer == null)
            return false;

        if (!(answer instanceof ServerAnswer))
            return false;

        ServerAnswer obj = (ServerAnswer) answer;

        if (obj.getHasMore()!= 1)
            return false;

        return true;

    }
}

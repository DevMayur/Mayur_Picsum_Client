package com.mayurkakade.mayur_picsum_client.apiRequests;

public abstract class BaseAsyncTask<R> implements CustomCallable<R>  {

    @Override
    public void setDataAfterLoading(R result) {

    }

    @Override
    public void setUiForLoading() {

    }

    @Override
    public R call() throws Exception {
        return null;
    }
}

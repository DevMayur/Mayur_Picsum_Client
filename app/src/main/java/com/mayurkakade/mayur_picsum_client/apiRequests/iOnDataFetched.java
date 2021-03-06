package com.mayurkakade.mayur_picsum_client.apiRequests;

public interface iOnDataFetched{
    void showProgressBar();
    void hideProgressBar();
    void setDataInPageWithResult(Object result);
}

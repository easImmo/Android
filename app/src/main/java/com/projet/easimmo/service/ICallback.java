package com.projet.easimmo.service;

/**
 * Created by victor on 25/04/2016.
 */
public interface ICallback<T> {

    void success(T t);

    void failure(Throwable error);
}

package com.egdroid.presentation.vm;

/**
 * Created at Tito on 2019-10-12
 */
public class Resource<T> {

    private Status status;
    private T data;
    private Throwable error;

    private Resource(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    static <T> Resource<T>  loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

    static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    static <T> Resource<T>  error(Throwable throwable) {
        return new Resource<>(Status.ERROR, null, throwable);
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}

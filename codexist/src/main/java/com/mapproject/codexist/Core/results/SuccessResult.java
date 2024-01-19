package com.mapproject.codexist.Core.results;

public class SuccessResult extends Result{
    public SuccessResult () {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}

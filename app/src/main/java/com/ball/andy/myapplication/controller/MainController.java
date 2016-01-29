package com.ball.andy.myapplication.controller;

import android.content.Context;

import com.ball.andy.myapplication.dto.MainDTO;
import com.ball.andy.myapplication.service.MainService;
import com.ball.andy.myapplication.service.MainServiceImpl;

/**
 * Created by Andy on 2016/1/20.
 */
public class MainController {


//    private transient MainService service = MainServiceImpl.get();

    private Context context;

    private MainDTO dto = new MainDTO();

    public MainController(Context context) {
        this.context = context;
    }


    public void loadData() {
        this.getService().loadData(this.getDto(), getContext());
    }

    public void ballot() {
        this.getService().ballot(this.getDto(), getContext());
    }


    public MainDTO getDto() {
        return dto;
    }

    public void setDto(MainDTO dto) {
        this.dto = dto;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public MainService getService() {
        return MainServiceImpl.get();
    }
}


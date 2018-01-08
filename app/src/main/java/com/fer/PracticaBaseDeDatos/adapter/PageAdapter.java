package com.fer.PracticaBaseDeDatos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by me on 29/12/17.
 */

//esta clase adaptador nos ayudará a incrustar un fragment en cada tab:

public class PageAdapter extends FragmentPagerAdapter{

    //el adaptador estará viendo los fragments como un arraylist de fragments, asiq lo declaramos:
    private ArrayList<Fragment> fragments;

    //al constructor aparte de ?XD, le estaremos pasando un Arraylist de fragments:
    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        //en nuestro método getItem, lo que vamos a estar devolviendo es un fragment en particular:
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        //estaremos devolviendo el tamaño de la lista de fragments:
        return fragments.size();
    }
}

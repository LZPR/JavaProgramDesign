package org.example.jpd.service;

import org.example.jpd.entity.ArrayEntity;

public class ArrayService {

    public ArrayEntity getMaxNumber(ArrayEntity arrayEntity, int[] arr) {
        arrayEntity.setResult(getMaxNumber(arr));
        return arrayEntity;
    }

    private int getMaxNumber(int[] arr) {
        int l = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[l]) {
                l = i;
            }
        }

        return arr[l];
    }
}

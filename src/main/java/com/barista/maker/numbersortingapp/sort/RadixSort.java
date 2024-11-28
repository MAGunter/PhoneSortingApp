package com.barista.maker.numbersortingapp.sort;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RadixSort {
    public void executeRadixSort(int[] arr){
        int largestValue = findMax(arr);
        for(int positionMultiplier = 1; largestValue / positionMultiplier > 0; positionMultiplier *= 10){
            distributeAndSort(arr, positionMultiplier);
        }
    }
    private int findMax(int[] arr){
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
    private void distributeAndSort(int[] arr, int positionMultiplier){
        int[] output = new int[arr.length];
        int[] count = new int[10];
        Arrays.fill(count, 0);
        for(int number : arr){
            int digit = (number / positionMultiplier) % 10;
            count[digit]++;
        }
        for(int i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for(int i = arr.length - 1; i >= 0; i--){
            int digit = (arr[i] / positionMultiplier) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}

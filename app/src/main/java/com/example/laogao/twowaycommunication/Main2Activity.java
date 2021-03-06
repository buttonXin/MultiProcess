package com.example.laogao.twowaycommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        new ThreadPoolExecutor(1, 1, 1, TimeUnit.DAYS,
                new BlockingQueue<Runnable>() {
                    @Override
                    public boolean add(Runnable runnable) {
                        return false;
                    }

                    @Override
                    public boolean offer(Runnable runnable) {
                        return false;
                    }

                    @Override
                    public void put(Runnable runnable) throws InterruptedException {

                    }

                    @Override
                    public boolean offer(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
                        return false;
                    }

                    @Override
                    public Runnable take() throws InterruptedException {
                        return null;
                    }

                    @Override
                    public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
                        return null;
                    }

                    @Override
                    public int remainingCapacity() {
                        return 0;
                    }

                    @Override
                    public boolean remove(Object o) {
                        return false;
                    }

                    @Override
                    public boolean contains(Object o) {
                        return false;
                    }

                    @Override
                    public int drainTo(Collection<? super Runnable> c) {
                        return 0;
                    }

                    @Override
                    public int drainTo(Collection<? super Runnable> c, int maxElements) {
                        return 0;
                    }

                    @Override
                    public Runnable remove() {
                        return null;
                    }

                    @Override
                    public Runnable poll() {
                        return null;
                    }

                    @Override
                    public Runnable element() {
                        return null;
                    }

                    @Override
                    public Runnable peek() {
                        return null;
                    }

                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public Iterator<Runnable> iterator() {
                        return null;
                    }

                    @Override
                    public Object[] toArray() {
                        return new Object[0];
                    }

                    @Override
                    public <T> T[] toArray(T[] a) {
                        return null;
                    }

                    @Override
                    public boolean containsAll(Collection<?> c) {
                        return false;
                    }

                    @Override
                    public boolean addAll(Collection<? extends Runnable> c) {
                        return false;
                    }

                    @Override
                    public boolean removeAll(Collection<?> c) {
                        return false;
                    }

                    @Override
                    public boolean retainAll(Collection<?> c) {
                        return false;
                    }

                    @Override
                    public void clear() {

                    }
                });
    }

    public void jump(View view) {
        startActivity(new Intent(this,Main3Activity.class));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");


    }
}

package com.rikkei.kiendd.mvvmbaseproject.view;

import com.rikkei.kiendd.mvvmbaseproject.base.BaseFragment;
import com.rikkei.kiendd.mvvmbaseproject.view.home.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ViewController<T extends BaseFragment> {

    private int layoutId;
    private FragmentManager fragmentManager;
    private List<T> fragmentList;
    private List<Class<T>> classList;
    private T currentFragment;

    public ViewController(FragmentManager fragmentManager, int layoutId) {
        this.fragmentManager = fragmentManager;
        this.layoutId = layoutId;
        fragmentList = new ArrayList<>();
        classList = new ArrayList<>();
    }

    private BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public void replaceFragment(Class<T> type, HashMap<String, Object> data) {
        if (currentFragment != null && currentFragment.getClass().getName().equalsIgnoreCase(type.getName())) {
            return;
        }

        try {
            currentFragment = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (data != null) {
            currentFragment.setData(data);
        }

        currentFragment.setViewController(this);

        // if redirect to Home, remove all previous fragment
        if (type == HomeFragment.class) {
            fragmentList.clear();
            classList.clear();
            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment != null) {
                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        }

        fragmentManager.beginTransaction().replace(layoutId, currentFragment).commit();
        fragmentList.clear();
        fragmentList.add(currentFragment);
        classList.add(type);
    }

    private void replaceFragmentInStartActivity(Class<T> type, HashMap<String, Object> data) {
        if (currentFragment != null && currentFragment.getClass().getName().equalsIgnoreCase(type.getName())) {
            return;
        }

        try {
            currentFragment = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (data != null) {
            currentFragment.setData(data);
        }

        currentFragment.setViewController(this);
        fragmentManager.beginTransaction().replace(layoutId, currentFragment).commit();
        fragmentList.clear();
        fragmentList.add(currentFragment);

        if (classList != null && classList.contains(type)) {
            int currentPosition = classList.indexOf(type);
            classList = classList.subList(0, currentPosition + 1);
        } else {
            classList.add(type);
        }
    }

    public void addFragment(Class<T> type, HashMap<String, Object> data) {
        if (currentFragment != null && currentFragment.getClass().getName().equalsIgnoreCase(type.getName())) {
            return;
        }

        fragmentManager.beginTransaction().hide(currentFragment).commit();

        try {
            currentFragment = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (data != null) {
            currentFragment.setData(data);
        }

        currentFragment.setViewController(this);

        // if redirect to Home, remove all previous fragment
        if (type == HomeFragment.class) {
            fragmentList.clear();
            classList.clear();
            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment != null) {
                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        }

        fragmentManager.beginTransaction().add(layoutId, currentFragment).commit();
        fragmentList.add(currentFragment);
        classList.add(type);
    }

    public boolean backFromAddFragment(HashMap<String, Object> data) {
        if (fragmentList.size() >= 2 && classList.size() >= 2) {
            fragmentList.remove(fragmentList.size() - 1);
            classList.remove(classList.size() - 1);
            fragmentManager.beginTransaction().remove(currentFragment).commit();
            currentFragment = fragmentList.get(fragmentList.size() - 1);

            if (data != null) {
                currentFragment.setData(data);
            }

            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);
            fragmentManager.beginTransaction().show(currentFragment).commit();
            currentFragment.backFromAddFragment();
            return true;
        } else {
            return false;
        }
    }

    public boolean backTwoStepFromAddFragment(HashMap<String, Object> data) {
        if (fragmentList.size() >= 3 && classList.size() >= 3) {
            fragmentList.remove(fragmentList.size() - 1);
            fragmentList.remove(fragmentList.size() - 1);
            classList.remove(classList.size() - 1);
            classList.remove(classList.size() - 1);
            fragmentManager.beginTransaction().remove(currentFragment).commit();
            currentFragment = fragmentList.get(fragmentList.size() - 1);

            if (data != null) {
                currentFragment.setData(data);
            }

            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);
            fragmentManager.beginTransaction().show(currentFragment).commit();
            currentFragment.backFromAddFragment();
            return true;
        } else {
            return false;
        }
    }

    public boolean backFromReplaceFragment(HashMap<String, Object> data) {
        if (classList.size() >= 2) {
            classList.remove(classList.size() - 1);

            try {
                currentFragment = classList.get(classList.size() - 1).newInstance();

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if (data != null) {
                currentFragment.setData(data);
            }

            currentFragment.setViewController(this);
            fragmentList.clear();
            fragmentList.add(currentFragment);
            fragmentManager.beginTransaction().replace(layoutId, currentFragment).commit();
            return true;
        } else {
            return false;
        }
    }
}

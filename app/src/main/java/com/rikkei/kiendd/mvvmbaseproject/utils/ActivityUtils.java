package com.rikkei.kiendd.mvvmbaseproject.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rikkei.kiendd.mvvmbaseproject.R;

import java.util.Stack;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    private static Stack<Fragment> fragmentStack = new Stack<>();

    public static Fragment currentFragment;

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void pushFragment(@NonNull FragmentManager fragmentManager,
                                    @NonNull Fragment fragment) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.flMainContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        fragmentStack.push(fragment);
        currentFragment = fragmentStack.peek();
    }

    public static void popFragment(@NonNull FragmentManager fragmentManager) {
        checkNotNull(fragmentManager);
        fragmentManager.popBackStack();

        fragmentStack.pop();

        if (!fragmentStack.empty()) {
            currentFragment = fragmentStack.peek();
        }
    }

    public static void popAllFragment(@NonNull FragmentManager fragmentManager) {
        checkNotNull(fragmentManager);
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentStack.clear();
        currentFragment = null;
    }
}

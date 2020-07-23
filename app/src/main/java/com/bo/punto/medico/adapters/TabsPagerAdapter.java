package com.bo.punto.medico.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bo.punto.medico.fragments.AboutUs;
import com.bo.punto.medico.fragments.Catalog;
import com.bo.punto.medico.fragments.Categories;
import com.bo.punto.medico.fragments.ContactUs;
import com.bo.punto.medico.fragments.Login;
import com.bo.punto.medico.fragments.Prices;
import com.bo.punto.medico.fragments.Profile;
import com.bo.punto.medico.utils.FragmentListener;

import java.util.ArrayList;
import java.util.List;


public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    private FragmentProfileListener listener = new FragmentProfileListener();
    private Fragment mFragmentAtPos0;
    private FragmentManager mFragmentManager;

    public TabsPagerAdapter(FragmentManager manager) {
        super(manager);
        mFragmentManager = manager;

        mFragmentAtPos0 = Login.newInstance(listener);

        addFragment(mFragmentAtPos0, "Mi perfil");
        addFragment(Catalog.newInstance(), "Catalogo");
        addFragment(Categories.newInstance(), "Categorias");
//        addFragment(Prices.newInstance(), "Precios");
//        addFragment(ContactUs.newInstance(), "Contactos");
//        addFragment(AboutUs.newInstance(), "Sobre nosotros");
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (object instanceof Login && mFragmentAtPos0 instanceof Profile) {
            return POSITION_NONE;
        }

        if (object instanceof Profile && mFragmentAtPos0 instanceof Login) {
            return POSITION_NONE;
        }

        return POSITION_UNCHANGED;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            if (mFragmentAtPos0 == null) {
                mFragmentAtPos0 = Login.newInstance(listener);
            }

            return mFragmentAtPos0;
        }

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    private void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    private final class FragmentProfileListener implements FragmentListener {

        public void onSwitchToNextFragment() {
            mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();

            if (mFragmentAtPos0 instanceof Login) {
                mFragmentAtPos0 = Profile.newInstance(listener);
            } else { // Instance of NextFragment
                mFragmentAtPos0 = Login.newInstance(listener);
            }

            notifyDataSetChanged();
        }

    }

}

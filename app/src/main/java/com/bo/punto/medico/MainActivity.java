package com.bo.punto.medico;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bo.punto.medico.ui.main.PlaceholderFragment;
import com.bo.punto.medico.utils.FragmentListener;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void initComponent() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

//        tabs.getTabAt(1).select();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(Login.newInstance(), "Mi perfil");
//        adapter.addFragment(Catalog.newInstance(), "Catalogo");
//        adapter.addFragment(Categories.newInstance(), "Categorias");
//        adapter.addFragment(Prices.newInstance(), "Precios");
//        adapter.addFragment(PlaceholderFragment.newInstance(4), "Sobre nosotros");
//        adapter.addFragment(ContactUs.newInstance(), "Contactos");
        viewPager.setAdapter(adapter);
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        FragmentProfileListener listener = new FragmentProfileListener();
        private Fragment mFragmentAtPos0;
        private FragmentManager mFragmentManager;

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
            mFragmentManager = manager;

            mFragmentAtPos0 = Login.newInstance(listener);

            addFragment(mFragmentAtPos0, "Mi perfil");
            addFragment(Catalog.newInstance(), "Catalogo");
            addFragment(Categories.newInstance(), "Categorias");
            addFragment(Prices.newInstance(), "Precios");
            addFragment(AboutUs.newInstance(), "Sobre nosotros");
            addFragment(ContactUs.newInstance(), "Contactos");
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

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public final class FragmentProfileListener implements FragmentListener {

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

    @Override
    public void update(Observable observable, Object o) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.tabs, Profile.newInstance(), "Perfil")
                .commit();
    }

}
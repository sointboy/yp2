package com.example.kkniga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.UUID;

public class BookPagerActivity extends AppCompatActivity {
    private static final String EXTRA_BOOK_ID = "ru.rsue.android.bookdepository.book_id";
    private ViewPager mViewPager;
    private List<Book> mBooks;
    public static Intent newIntent(Context packageContext, UUID bookId)
    {
        Intent intent = new Intent(packageContext, BookPagerActivity.class);
        intent.putExtra(EXTRA_BOOK_ID, bookId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_pager);
        UUID bookId = (UUID) getIntent().getSerializableExtra(EXTRA_BOOK_ID);
        mViewPager = (ViewPager) findViewById( R.id.activity_book_pager_view_pager);
        mBooks = BookLab.get(this).getBooks();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Book book = mBooks.get(position);
                return BookFragment.newInstance(book.getId());
            }
            @Override
            public int getCount() {
                return mBooks.size();
            }
        });
        for (int i = 0; i < mBooks.size(); i++) {
            if (mBooks.get(i).getId().equals(bookId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }



    }
}



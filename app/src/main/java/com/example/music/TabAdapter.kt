package com.example.music
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val listFragment: ArrayList<Fragment> = ArrayList()
    private val listTitle: ArrayList<String> = ArrayList()


    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    fun addFragmentAndTitle(fragment: Fragment, title: String) {
        listFragment.add(fragment)
        listTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return listTitle[position]
    }
}
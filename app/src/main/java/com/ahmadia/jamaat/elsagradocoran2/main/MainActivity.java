package com.ahmadia.jamaat.elsagradocoran2.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ahmadia.jamaat.elsagradocoran2.R;
import com.ahmadia.jamaat.elsagradocoran2.TIV.TouchImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private GestureDetectorCompat gestureDetector;
    private int lastPage;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ExpandableListView mDrawerList;
    ExpandableListAdapter arrayAdapter;
    LinearLayout linLay;
    private int lastExpandedPosition;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Toolbar myToolbar;
    ActionBar actionBar;
    TextView mTitle;
    Intent browserIntent;
    ImageView leftClick;
    ImageView rightClick;


    //private String[] contents;
    ViewPager viewPager;
    TouchImageAdapter awesomeadapter = new TouchImageAdapter();
///////////////////////////////////////////////////////////////////VARIABLES HERE
    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String STATE_CURRENT_PAGE_INDEX = "current_page_index";
    private static final String FILENAME = "Holy-Quran-Spanish.pdf";
    private ParcelFileDescriptor mFileDescriptor;
    public PdfRenderer mPdfRenderer;
    public PdfRenderer.Page mCurrentPage;
    private int mPageIndex;
    private TouchImageView mImageView;

    //////////////////////////EXTENDED LIST VIEW////////////////////////////

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("");
        listDataHeader.add("Introducción");
        listDataHeader.add("Capítulos");
        listDataHeader.add("Partes");
        listDataHeader.add("Apéndices");
        listDataHeader.add("¿Quiénes somos?");

        // Adding child data
        List<String> indice = new ArrayList<>();
        indice.add("1. Al-Fatihah");
        indice.add("2 .Al-Baqarah");
        indice.add("3. Al-Imran");
        indice.add("4. Al-Nisa");
        indice.add("5. Al-Maidah");
        indice.add("6. Al-Anam");
        indice.add("7. Al-A\'raf");
        indice.add("8. Al-Anfal");
        indice.add("9. Al-Tauba");
        indice.add("10. Yunus");
        indice.add("11. Hud");
        indice.add("12. Yusuf");
        indice.add("13. Al-Rad");
        indice.add("14. Ibrahim");
        indice.add("15. Al-Hillr");
        indice.add("16. Al-Nahl");
        indice.add("17. Bani Isra\'il");
        indice.add("18. Al-Kahf");
        indice.add("19. Maryam");
        indice.add("20. Ta ha");
        indice.add("21. Al-Anbiya\'");
        indice.add("22. Al-Hall");
        indice.add("23. Al-Muminun");
        indice.add("24. Al-Nur");
        indice.add("25. Al-Furqan");
        indice.add("26. Al-Shuara");
        indice.add("27. Al-Naml");
        indice.add("28. Al-Qasas");
        indice.add("29. Al-Akabut");
        indice.add("30. Al-Rum");
        indice.add("31. Luqman");
        indice.add("32. Al-Salldah");
        indice.add("33. Al-Ahzab");
        indice.add("34. Saba");
        indice.add("35. Fatir");
        indice.add("36. Ya-Sin");
        indice.add("37. Al-Saffat");
        indice.add("38. Sad");
        indice.add("39. Al-Zumar");
        indice.add("40. Al-Mumin");
        indice.add("41. HamimSalldah");
        indice.add("42. Al-Shura");
        indice.add("43. Al-Zujruf");
        indice.add("44. Al-Dujan");
        indice.add("45. Al-Yaziyah");
        indice.add("46. Al-Ahqaf");
        indice.add("47. Mohammad");
        indice.add("48. Al-Fath");
        indice.add("49. Al-Huyurat");
        indice.add("50. Qaf");
        indice.add("51. Al-Dhariyat");
        indice.add("52. Al-Tur");
        indice.add("53. Al-Nallm");
        indice.add("54. Al-Qamar");
        indice.add("55. Al-Rahman");
        indice.add("56. Al-Waqiah");
        indice.add("57. Al-Hadid");
        indice.add("58. Al-Muyadalah");
        indice.add("59. Al-Hashr");
        indice.add("60. Al-Mumtahenah");
        indice.add("61. Al-Saff");
        indice.add("62. Al-Yumuah");
        indice.add("63. Al-Munafiqun");
        indice.add("64. Al-Taghabun");
        indice.add("65. Al-Talaq");
        indice.add("66. Al-Tahrim");
        indice.add("67. Al-Mulk");
        indice.add("68. Al-Qalam");
        indice.add("69. Al-Haqqah");
        indice.add("70. Al-Ma\'arill");
        indice.add("71. Nuh");
        indice.add("72. Al-Yinn");
        indice.add("73. Al-Muzzamil");
        indice.add("74. Al-Mudazzir");
        indice.add("75. Al-Qiyamah");
        indice.add("76. Al-Dahr");
        indice.add("77. Al-Mursalat");
        indice.add("78. Al-Naba");
        indice.add("79. Al-Naziat");
        indice.add("80. Abasa");
        indice.add("81. Al-Takwir");
        indice.add("82. Al-Infitar");
        indice.add("83. Al-Mutaffefin");
        indice.add("84. Al-Inshiqaq");
        indice.add("85. Al-Burull");
        indice.add("86. Al-Tariq");
        indice.add("87. Al-Ala");
        indice.add("88. Al-Ghashiyah");
        indice.add("89. Al-Fallr");
        indice.add("90. Al-Balad");
        indice.add("91. Al-Shams");
        indice.add("92. Al-Lail");
        indice.add("93. Al-Duha");
        indice.add("94. Alam-Nashrah");
        indice.add("95. Al-Tin");
        indice.add("96. Al-Alaq");
        indice.add("97. Al-Qadr");
        indice.add("98. Al-Bayyinah");
        indice.add("99. Al-Zilzal");
        indice.add("100. Al-Adiyat");
        indice.add("101. Al-Qariah");
        indice.add("102. Al-Takazur");
        indice.add("103. Al-Asr");
        indice.add("104. Al-Humazah");
        indice.add("105. Al-Fil");
        indice.add("106. Al-Quraish");
        indice.add("107. Al-Maun");
        indice.add("108. Al-Kauzar");
        indice.add("109. Al-Kafirun");
        indice.add("110. Al-Nasr");
        indice.add("111. Al-Lahab");
        indice.add("112. Al-Ijlas");
        indice.add("113. Al-Falaq");
        indice.add("114. Al-Nas");

        List<String> partes = new ArrayList<String>();
        partes.add("Parte 1");
        partes.add("Parte 2");
        partes.add("Parte 3");
        partes.add("Parte 4");
        partes.add("Parte 5");
        partes.add("Parte 6");
        partes.add("Parte 7");
        partes.add("Parte 8");
        partes.add("Parte 9");
        partes.add("Parte 10");
        partes.add("Parte 11");
        partes.add("Parte 12");
        partes.add("Parte 13");
        partes.add("Parte 14");
        partes.add("Parte 15");
        partes.add("Parte 16");
        partes.add("Parte 17");
        partes.add("Parte 18");
        partes.add("Parte 19");
        partes.add("Parte 20");
        partes.add("Parte 21");
        partes.add("Parte 22");
        partes.add("Parte 23");
        partes.add("Parte 24");
        partes.add("Parte 25");
        partes.add("Parte 26");
        partes.add("Parte 27");
        partes.add("Parte 28");
        partes.add("Parte 29");
        partes.add("Parte 30");

        List<String> appendix = new ArrayList<String>();
        appendix.add("Oración Final");
        appendix.add("Indice alfabético");
        appendix.add("Glosario árabe");

        listDataChild.put(listDataHeader.get(2), indice); // Header, Child data
        listDataChild.put(listDataHeader.get(3), partes);
        listDataChild.put(listDataHeader.get(4), appendix);
    }




/////////////////////////////////////ONCREATE MEHODS/////////////////////////////////////////
    private void NavBar(){
        lastExpandedPosition = -1;
        //NAVIGATION ACTION BAR CODE
        mDrawerLayout = findViewById(R.id.drawerLayout);
        myToolbar = findViewById(R.id.my_toolbar);
        mTitle = myToolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(myToolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        //contents = getResources().getStringArray(R.array.holy_quran);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerList = findViewById(R.id.left_drawer_list);
        mDrawerLayout.addDrawerListener(mToggle);
        linLay = (LinearLayout) findViewById(R.id.left_drawer_layout);
        mToggle.syncState();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //mDrawerLayout.closeDrawer(Gravity.LEFT);
        //mDrawerLayout.closeDrawers();
        prepareListData();
        arrayAdapter= new CustomAdapter(this,listDataHeader,listDataChild);
        mDrawerList.setAdapter(arrayAdapter);
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, contents>());
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                if (groupPosition == 1){
                viewPager.setCurrentItem(0, false);
                mDrawerLayout.closeDrawer(Gravity.START);}
                else if (groupPosition == 5)
                {
                    startActivity(browserIntent);
                    mDrawerLayout.closeDrawer(Gravity.START);
                }
                return false;
            }
        });
        mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (groupPosition == 2) {
                    switch (childPosition) {
                        case 0: {
                            viewPager.setCurrentItem(11, false);
                            break;
                        }
                        case 1: {
                            viewPager.setCurrentItem(15, false);
                            break;
                        }
                        case 2: {
                            viewPager.setCurrentItem(79, false);
                            break;
                        }
                        case 3: {
                            viewPager.setCurrentItem(118, false);
                            break;
                        }
                        case 4: {
                            viewPager.setCurrentItem(157, false);
                            break;
                        }
                        case 5: {
                            viewPager.setCurrentItem(186, false);
                            break;
                        }
                        case 6: {
                            viewPager.setCurrentItem(218, false);
                            break;
                        }
                        case 7: {
                            viewPager.setCurrentItem(255, false);
                            break;
                        }
                        case 8: {
                            viewPager.setCurrentItem(272, false);
                            break;
                        }
                        case 9: {
                            viewPager.setCurrentItem(297, false);
                            break;
                        }
                        case 10: {
                            viewPager.setCurrentItem(318, false);
                            break;
                        }
                        case 11: {
                            viewPager.setCurrentItem(341, false);
                            break;
                        }
                        case 12: {
                            viewPager.setCurrentItem(361, false);
                            break;
                        }
                        case 13: {
                            viewPager.setCurrentItem(372, false);
                            break;
                        }
                        case 14: {
                            viewPager.setCurrentItem(382, false);
                            break;
                        }
                        case 15: {
                            viewPager.setCurrentItem(393, false);
                            break;
                        }
                        case 16: {
                            viewPager.setCurrentItem(415, false);
                            break;
                        }
                        case 17: {
                            viewPager.setCurrentItem(434, false);
                            break;
                        }
                        case 18: {
                            viewPager.setCurrentItem(454, false);
                            break;
                        }
                        case 19: {
                            viewPager.setCurrentItem(468, false);
                            break;
                        }
                        case 20: {
                            viewPager.setCurrentItem(487, false);
                            break;
                        }
                        case 21: {
                            viewPager.setCurrentItem(504, false);
                            break;
                        }
                        case 22: {
                            viewPager.setCurrentItem(519, false);
                            break;
                        }
                        case 23: {
                            viewPager.setCurrentItem(534, false);
                            break;
                        }
                        case 24: {
                            viewPager.setCurrentItem(550, false);
                            break;
                        }
                        case 25: {
                            viewPager.setCurrentItem(562, false);
                            break;
                        }
                        case 26: {
                            viewPager.setCurrentItem(584, false);
                            break;
                        }
                        case 27: {
                            viewPager.setCurrentItem(599, false);
                            break;
                        }
                        case 28: {
                            viewPager.setCurrentItem(616, false);
                            break;
                        }
                        case 29: {
                            viewPager.setCurrentItem(629, false);
                            break;
                        }
                        case 30: {
                            viewPager.setCurrentItem(640, false);
                            break;
                        }
                        case 31: {
                            viewPager.setCurrentItem(648, false);
                            break;
                        }
                        case 32: {
                            viewPager.setCurrentItem(654, false);
                            break;
                        }
                        case 33: {
                            viewPager.setCurrentItem(670, false);
                            break;
                        }
                        case 34: {
                            viewPager.setCurrentItem(682, false);
                            break;
                        }
                        case 35: {
                            viewPager.setCurrentItem(692, false);
                            break;
                        }
                        case 36: {
                            viewPager.setCurrentItem(703, false);
                            break;
                        }
                        case 37: {
                            viewPager.setCurrentItem(720, false);
                            break;
                        }
                        case 38: {
                            viewPager.setCurrentItem(732, false);
                            break;
                        }
                        case 39: {
                            viewPager.setCurrentItem(747, false);
                            break;
                        }
                        case 40: {
                            viewPager.setCurrentItem(762, false);
                            break;
                        }
                        case 41: {
                            viewPager.setCurrentItem(772, false);
                            break;
                        }
                        case 42: {
                            viewPager.setCurrentItem(783, false);
                            break;
                        }
                        case 43: {
                            viewPager.setCurrentItem(796, false);
                            break;
                        }
                        case 44: {
                            viewPager.setCurrentItem(803, false);
                            break;
                        }
                        case 45: {
                            viewPager.setCurrentItem(811, false);
                            break;
                        }
                        case 46: {
                            viewPager.setCurrentItem(820, false);
                            break;
                        }
                        case 47: {
                            viewPager.setCurrentItem(827, false);
                            break;
                        }
                        case 48: {
                            viewPager.setCurrentItem(835, false);
                            break;
                        }
                        case 49: {
                            viewPager.setCurrentItem(840, false);
                            break;
                        }
                        case 50: {
                            viewPager.setCurrentItem(846, false);
                            break;
                        }
                        case 51: {
                            viewPager.setCurrentItem(853, false);
                            break;
                        }
                        case 52: {
                            viewPager.setCurrentItem(859, false);
                            break;
                        }
                        case 53: {
                            viewPager.setCurrentItem(866, false);
                            break;
                        }
                        case 54: {
                            viewPager.setCurrentItem(873, false);
                            break;
                        }
                        case 55: {
                            viewPager.setCurrentItem(882, false);
                            break;
                        }
                        case 56: {
                            viewPager.setCurrentItem(890, false);
                            break;
                        }
                        case 57: {
                            viewPager.setCurrentItem(897, false);
                            break;
                        }
                        case 58: {
                            viewPager.setCurrentItem(903, false);
                            break;
                        }
                        case 59: {
                            viewPager.setCurrentItem(909, false);
                            break;
                        }
                        case 60: {
                            viewPager.setCurrentItem(914, false);
                            break;
                        }
                        case 61: {
                            viewPager.setCurrentItem(918, false);
                            break;
                        }
                        case 62: {
                            viewPager.setCurrentItem(921, false);
                            break;
                        }
                        case 63: {
                            viewPager.setCurrentItem(924, false);
                            break;
                        }
                        case 64: {
                            viewPager.setCurrentItem(928, false);
                            break;
                        }
                        case 65: {
                            viewPager.setCurrentItem(932, false);
                            break;
                        }
                        case 66: {
                            viewPager.setCurrentItem(936, false);
                            break;
                        }
                        case 67: {
                            viewPager.setCurrentItem(941, false);
                            break;
                        }
                        case 68: {
                            viewPager.setCurrentItem(947, false);
                            break;
                        }
                        case 69: {
                            viewPager.setCurrentItem(953, false);
                            break;
                        }
                        case 70: {
                            viewPager.setCurrentItem(958, false);
                            break;
                        }
                        case 71: {
                            viewPager.setCurrentItem(962, false);
                            break;
                        }
                        case 72: {
                            viewPager.setCurrentItem(967, false);
                            break;
                        }
                        case 73: {
                            viewPager.setCurrentItem(971, false);
                            break;
                        }
                        case 74: {
                            viewPager.setCurrentItem(977, false);
                            break;
                        }
                        case 75: {
                            viewPager.setCurrentItem(981, false);
                            break;
                        }
                        case 76: {
                            viewPager.setCurrentItem(986, false);
                            break;
                        }
                        case 77: {
                            viewPager.setCurrentItem(991, false);
                            break;
                        }
                        case 78: {
                            viewPager.setCurrentItem(996, false);
                            break;
                        }
                        case 79: {
                            viewPager.setCurrentItem(1001, false);
                            break;
                        }
                        case 80: {
                            viewPager.setCurrentItem(1005, false);
                            break;
                        }
                        case 81: {
                            viewPager.setCurrentItem(1009, false);
                            break;
                        }
                        case 82: {
                            viewPager.setCurrentItem(1012, false);
                            break;
                        }
                        case 83: {
                            viewPager.setCurrentItem(1016, false);
                            break;
                        }
                        case 84: {
                            viewPager.setCurrentItem(1019, false);
                            break;
                        }
                        case 85: {
                            viewPager.setCurrentItem(1022, false);
                            break;
                        }
                        case 86: {
                            viewPager.setCurrentItem(1025, false);
                            break;
                        }
                        case 87: {
                            viewPager.setCurrentItem(1028, false);
                            break;
                        }
                        case 88: {
                            viewPager.setCurrentItem(1031, false);
                            break;
                        }
                        case 89: {
                            viewPager.setCurrentItem(1035, false);
                            break;
                        }
                        case 90: {
                            viewPager.setCurrentItem(1038, false);
                            break;
                        }
                        case 91: {
                            viewPager.setCurrentItem(1041, false);
                            break;
                        }
                        case 92: {
                            viewPager.setCurrentItem(1044, false);
                            break;
                        }
                        case 93: {
                            viewPager.setCurrentItem(1046, false);
                            break;
                        }
                        case 94: {
                            viewPager.setCurrentItem(1048, false);
                            break;
                        }
                        case 95: {
                            viewPager.setCurrentItem(1050, false);
                            break;
                        }
                        case 96: {
                            viewPager.setCurrentItem(1053, false);
                            break;
                        }
                        case 97: {
                            viewPager.setCurrentItem(1055, false);
                            break;
                        }
                        case 98: {
                            viewPager.setCurrentItem(1058, false);
                            break;
                        }
                        case 99: {
                            viewPager.setCurrentItem(1060, false);
                            break;
                        }
                        case 100: {
                            viewPager.setCurrentItem(1062, false);
                            break;
                        }
                        case 101: {
                            viewPager.setCurrentItem(1064, false);
                            break;
                        }
                        case 102: {
                            viewPager.setCurrentItem(1066, false);
                            break;
                        }
                        case 103: {
                            viewPager.setCurrentItem(1068, false);
                            break;
                        }
                        case 104: {
                            viewPager.setCurrentItem(1070, false);
                            break;
                        }
                        case 105: {
                            viewPager.setCurrentItem(1073, false);
                            break;
                        }
                        case 106: {
                            viewPager.setCurrentItem(1075, false);
                            break;
                        }
                        case 107: {
                            viewPager.setCurrentItem(1077, false);
                            break;
                        }
                        case 108: {
                            viewPager.setCurrentItem(1079, false);
                            break;
                        }
                        case 109: {
                            viewPager.setCurrentItem(1081, false);
                            break;
                        }
                        case 110: {
                            viewPager.setCurrentItem(1083, false);
                            break;
                        }
                        case 111: {
                            viewPager.setCurrentItem(1085, false);
                            break;
                        }
                        case 112: {
                            viewPager.setCurrentItem(1087, false);
                            break;
                        }
                        case 113: {
                            viewPager.setCurrentItem(1089, false);
                            break;
                        }
                    }
                }
                if (groupPosition == 3) {
                    switch (childPosition) {
                        case 0: {
                            viewPager.setCurrentItem(19, false);
                            break;
                        }
                        case 1: {
                            viewPager.setCurrentItem(43, false);
                            break;
                        }
                        case 2: {
                            viewPager.setCurrentItem(68, false);
                            break;
                        }
                        case 3: {
                            viewPager.setCurrentItem(98, false);
                            break;
                        }
                        case 4: {
                            viewPager.setCurrentItem(125, false);
                            break;
                        }
                        case 5: {
                            viewPager.setCurrentItem(149, false);
                            break;
                        }
                        case 6: {
                            viewPager.setCurrentItem(177, false);
                            break;
                        }
                        case 7: {
                            viewPager.setCurrentItem(206, false);
                            break;
                        }
                        case 8: {
                            viewPager.setCurrentItem(235, false);
                            break;
                        }
                        case 9: {
                            viewPager.setCurrentItem(265, false);
                            break;
                        }
                        case 10: {
                            viewPager.setCurrentItem(289, false);
                            break;
                        }
                        case 11: {
                            viewPager.setCurrentItem(321, false);
                            break;
                        }
                        case 12: {
                            viewPager.setCurrentItem(351, false);
                            break;
                        }
                        case 13: {
                            viewPager.setCurrentItem(383, false);
                            break;
                        }
                        case 14: {
                            viewPager.setCurrentItem(417, false);
                            break;
                        }
                        case 15: {
                            viewPager.setCurrentItem(448, false);
                            break;
                        }
                        case 16: {
                            viewPager.setCurrentItem(490, false);
                            break;
                        }
                        case 17: {
                            viewPager.setCurrentItem(521, false);
                            break;
                        }
                        case 18: {
                            viewPager.setCurrentItem(555, false);
                            break;
                        }
                        case 19: {
                            viewPager.setCurrentItem(594, false);
                            break;
                        }
                        case 20: {
                            viewPager.setCurrentItem(624, false);
                            break;
                        }
                        case 21: {
                            viewPager.setCurrentItem(661, false);
                            break;
                        }
                        case 22: {
                            viewPager.setCurrentItem(696, false);
                            break;
                        }
                        case 23: {
                            viewPager.setCurrentItem(739, false);
                            break;
                        }
                        case 24: {
                            viewPager.setCurrentItem(770, false);
                            break;
                        }
                        case 25: {
                            viewPager.setCurrentItem(813, false);
                            break;
                        }
                        case 26: {
                            viewPager.setCurrentItem(849, false);
                            break;
                        }
                        case 27: {
                            viewPager.setCurrentItem(898, false);
                            break;
                        }
                        case 28: {
                            viewPager.setCurrentItem(937, false);
                            break;
                        }
                        case 29: {
                            viewPager.setCurrentItem(992, false);
                            break;
                        }
                    }
                }
                if (groupPosition == 4) {
                    switch (childPosition) {
                        case 0: {
                            viewPager.setCurrentItem(1091, false);
                            break;
                        }
                        case 1: {
                            viewPager.setCurrentItem(1092, false);
                            break;
                        }
                        case 2: {
                            viewPager.setCurrentItem(1117, false);
                            break;
                        }
                    }
                }


                        mDrawerLayout.closeDrawer(Gravity.START);
                return false;
            }
        });

        mDrawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
/*                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    mDrawerList.collapseGroup(lastExpandedPosition);
                }*/
                lastExpandedPosition = groupPosition;

            }
        });
    }




    ///////////////////////////////////////ONCREATE////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setTheme(R.style.AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            openRenderer(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mImageView = findViewById(R.id.image);
        viewPager = findViewById(R.id.evp);

        viewPager.setAdapter(awesomeadapter);

        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alislam.es"));


        mPageIndex = 0; //PROBABLY NOT NEEDED
        NavBar();
        viewPager.addOnPageChangeListener(this);

        leftClick = findViewById(R.id.leftClick);
        rightClick = findViewById(R.id.rightClick);

        leftClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });

        rightClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if(mToggle.onOptionsItemSelected(item))

            if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                mDrawerLayout.closeDrawer(Gravity.START);
            } else {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        
                return super.onOptionsItemSelected(item);

    }  ///////////////////////////////////MAKE SURE THE DRAWER OPENS AND CLOSES LIKE IT'S SUPPOSED TO

    @Override
    public void onBackPressed() {
        mImageView = viewPager.findViewWithTag(viewPager.getCurrentItem());
        if (mImageView.isZoomed()) {
            mImageView.resetZoom();
        }else{
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("")
                .setMessage("¿Estás seguro de que quieres salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();}
    }
        // viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*selectItem(position);*/
        }
    }


    @Override
    public void onStart() {
        super.onStart();
/*        try {
            //openRenderer(this);
            showPage(mPageIndex);*/
/*        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/

        SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, 0);
        lastPage = settings.getInt("lastPage", 0);
        viewPager.setCurrentItem(lastPage -1); ////RETRIEVED LAST SAVED PAGE
    }

    @Override
    public void onResume() {
        SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, 0);
        lastPage = settings.getInt("lastPage", 0);
        viewPager.setCurrentItem(lastPage - 1); ////RETRIEVED LAST SAVED PAGE

        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("lastPage", mCurrentPage.getIndex()); //////SAVE LAST PAGE ON PAUSE
        editor.apply();

    }


    @Override
    public void onStop() {
        /*try {
            closeRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ////REMOVED IT BECAUSE IT WAS CAUSING CRASHES
        super.onStop();
        SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("lastPage", mCurrentPage.getIndex());  /////ON STOP, SAVE THE LAST PAGE
        editor.apply();
    }

    public void onDestroy(){
                try {
            closeRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
        SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("lastPage", mCurrentPage.getIndex());  /////ON STOP, SAVE THE LAST PAGE
        editor.commit();
    }

    /**
     * Sets up a {@link android.graphics.pdf.PdfRenderer} and related resources.
     */
    private void openRenderer(Context context) throws IOException {
        // In this sample, we read a PDF from the assets directory.
        File file = new File(context.getCacheDir(), FILENAME);
        if (!file.exists()) {
            // Since PdfRenderer cannot handle the compressed asset file directly, we copy it into
            // the cache directory.
            InputStream asset = context.getAssets().open(FILENAME);
            FileOutputStream output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int size;
            while ((size = asset.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            asset.close();
            output.close();
        }
        mFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        // This is the PdfRenderer we use to render the PDF.
        if (mFileDescriptor != null) {
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        }
    }

    /**
     * Closes the {@link android.graphics.pdf.PdfRenderer} and related resources.
     *
     * @throws java.io.IOException When the PDF file cannot be closed.
     */
    private void closeRenderer() throws IOException {
        if (null != mCurrentPage) {
            mCurrentPage.close();
        }
        mPdfRenderer.close();
        mFileDescriptor.close();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position >= 0 && position < 11) {
            mTitle.setText(R.string.Introduction);
        } else if (position >= 11 && position < 15) {
            mTitle.setText(R.string.Surah001);
        } else if (position >= 15 && position < 79) {
            mTitle.setText(R.string.Surah002);
        } else if (position >= 79 && position < 118) {
            mTitle.setText(R.string.Surah003);
        }else if (position >= 118 && position < 157) {
            mTitle.setText(R.string.Surah004);
        } else if (position >= 157 && position < 186) {
            mTitle.setText(R.string.Surah005);
        } else if (position >= 186 && position < 218) {
            mTitle.setText(R.string.Surah006);
        }else if (position >= 218 && position < 255) {
            mTitle.setText(R.string.Surah007);
        } else if (position >= 255 && position < 272) {
            mTitle.setText(R.string.Surah008);
        } else if (position >= 272 && position < 297) {
            mTitle.setText(R.string.Surah009);
        }else if (position >= 297 && position < 318) {
            mTitle.setText(R.string.Surah010);
        } else if (position >= 318 && position < 341) {
            mTitle.setText(R.string.Surah011);
        } else if (position >= 341 && position < 361) {
            mTitle.setText(R.string.Surah012);
        }else if (position >= 361 && position < 372) {
            mTitle.setText(R.string.Surah013);
        } else if (position >= 372 && position < 382) {
            mTitle.setText(R.string.Surah014);
        } else if (position >= 382 && position < 393) {
            mTitle.setText(R.string.Surah015);
        }else if (position >= 393 && position < 415) {
            mTitle.setText(R.string.Surah016);
        } else if (position >= 415 && position < 434) {
            mTitle.setText(R.string.Surah017);
        } else if (position >= 434 && position < 454) {
            mTitle.setText(R.string.Surah018);
        }else if (position >= 454 && position < 468) {
            mTitle.setText(R.string.Surah019);
        } else if (position >= 468 && position < 487) {
            mTitle.setText(R.string.Surah020);
        } else if (position >= 487 && position < 504) {
            mTitle.setText(R.string.Surah021);
        }else if (position >= 504 && position < 519) {
            mTitle.setText(R.string.Surah022);
        } else if (position >= 519 && position < 534) {
            mTitle.setText(R.string.Surah023);
        } else if (position >= 534 && position < 550) {
            mTitle.setText(R.string.Surah024);
        }else if (position >= 550 && position < 562) {
            mTitle.setText(R.string.Surah025);
        } else if (position >= 562 && position < 584) {
            mTitle.setText(R.string.Surah026);
        } else if (position >= 584 && position < 599) {
            mTitle.setText(R.string.Surah027);
        }else if (position >= 599 && position < 616) {
            mTitle.setText(R.string.Surah028);
        } else if (position >= 616 && position < 629) {
            mTitle.setText(R.string.Surah029);
        } else if (position >= 629 && position < 640) {
            mTitle.setText(R.string.Surah030);
        }else if (position >= 640 && position < 648) {
            mTitle.setText(R.string.Surah031);
        } else if (position >= 648 && position < 654) {
            mTitle.setText(R.string.Surah032);
        } else if (position >= 654 && position < 670) {
            mTitle.setText(R.string.Surah033);
        }else if (position >= 670 && position < 682) {
            mTitle.setText(R.string.Surah034);
        } else if (position >= 682 && position < 692) {
            mTitle.setText(R.string.Surah035);
        } else if (position >= 692 && position < 703) {
            mTitle.setText(R.string.Surah036);
        }else if (position >= 703 && position < 720) {
            mTitle.setText(R.string.Surah037);
        } else if (position >= 720 && position < 732) {
            mTitle.setText(R.string.Surah038);
        } else if (position >= 732 && position < 747) {
            mTitle.setText(R.string.Surah039);
        }else if (position >= 747 && position < 762) {
            mTitle.setText(R.string.Surah040);
        } else if (position >= 762 && position < 772) {
            mTitle.setText(R.string.Surah041);
        } else if (position >= 772 && position < 783) {
            mTitle.setText(R.string.Surah042);
        } else if (position >= 783 && position < 796) {
            mTitle.setText(R.string.Surah043);
        } else if (position >= 796 && position < 803) {
            mTitle.setText(R.string.Surah044);
        }else if (position >= 803 && position < 811) {
            mTitle.setText(R.string.Surah045);
        } else if (position >= 811 && position < 820) {
            mTitle.setText(R.string.Surah046);
        } else if (position >= 820 && position < 827) {
            mTitle.setText(R.string.Surah047);
        }else if (position >= 827 && position < 835) {
            mTitle.setText(R.string.Surah048);
        } else if (position >= 835 && position < 840) {
            mTitle.setText(R.string.Surah049);
        } else if (position >= 840 && position < 846) {
            mTitle.setText(R.string.Surah050);
        }else if (position >= 846 && position < 853) {
            mTitle.setText(R.string.Surah051);
        } else if (position >= 853 && position < 859) {
            mTitle.setText(R.string.Surah052);
        } else if (position >= 859 && position < 866) {
            mTitle.setText(R.string.Surah053);
        }else if (position >= 866 && position < 873) {
            mTitle.setText(R.string.Surah054);
        } else if (position >= 873 && position < 882) {
            mTitle.setText(R.string.Surah055);
        } else if (position >= 882 && position < 890) {
            mTitle.setText(R.string.Surah056);
        } else if (position >= 890 && position < 897) {
            mTitle.setText(R.string.Surah057);
        } else if (position >= 897 && position < 903) {
            mTitle.setText(R.string.Surah058);
        }else if (position >= 903 && position < 909) {
            mTitle.setText(R.string.Surah059);
        } else if (position >= 909 && position < 914) {
            mTitle.setText(R.string.Surah060);
        } else if (position >= 914 && position < 918) {
            mTitle.setText(R.string.Surah061);
        }else if (position >= 918 && position < 921) {
            mTitle.setText(R.string.Surah062);
        } else if (position >= 921 && position < 924) {
            mTitle.setText(R.string.Surah063);
        } else if (position >= 924 && position < 928) {
            mTitle.setText(R.string.Surah064);
        }else if (position >= 928 && position < 932) {
            mTitle.setText(R.string.Surah065);
        } else if (position >= 932 && position < 936) {
            mTitle.setText(R.string.Surah066);
        } else if (position >= 936 && position < 941) {
            mTitle.setText(R.string.Surah067);
        }else if (position >= 941 && position < 947) {
            mTitle.setText(R.string.Surah068);
        } else if (position >= 947 && position < 953) {
            mTitle.setText(R.string.Surah069);
        } else if (position >= 953 && position < 958) {
            mTitle.setText(R.string.Surah070);
        } else if (position >= 958 && position < 962) {
            mTitle.setText(R.string.Surah071);
        } else if (position >= 962 && position < 967) {
            mTitle.setText(R.string.Surah072);
        } else if (position >= 967 && position < 971) {
            mTitle.setText(R.string.Surah073);
        }else if (position >= 971 && position < 977) {
            mTitle.setText(R.string.Surah074);
        } else if (position >= 977 && position < 981) {
            mTitle.setText(R.string.Surah075);
        } else if (position >= 981 && position < 986) {
            mTitle.setText(R.string.Surah076);
        }else if (position >= 986 && position < 991) {
            mTitle.setText(R.string.Surah077);
        } else if (position >= 991 && position < 996) {
            mTitle.setText(R.string.Surah078);
        } else if (position >= 996 && position < 1001) {
            mTitle.setText(R.string.Surah079);
        }else if (position >= 1001 && position < 1005) {
            mTitle.setText(R.string.Surah080);
        } else if (position >= 1005 && position < 1009) {
            mTitle.setText(R.string.Surah081);
        } else if (position >= 1009 && position < 1012) {
            mTitle.setText(R.string.Surah082);
        }else if (position >= 1012 && position < 1016) {
            mTitle.setText(R.string.Surah083);
        } else if (position >= 1016 && position < 1019) {
            mTitle.setText(R.string.Surah084);
        } else if (position >= 1019 && position < 1022) {
            mTitle.setText(R.string.Surah085);
        } else if (position >= 1022 && position < 1025) {
            mTitle.setText(R.string.Surah086);
        } else if (position >= 1025 && position < 1028) {
            mTitle.setText(R.string.Surah087);}
        else if (position >= 1028 && position < 1031) {
            mTitle.setText(R.string.Surah088);
        }else if (position >= 1031 && position < 1035) {
            mTitle.setText(R.string.Surah089);
        } else if (position >= 1035 && position < 1038) {
            mTitle.setText(R.string.Surah090);
        } else if (position >= 1038 && position < 1041) {
            mTitle.setText(R.string.Surah091);
        }else if (position >= 1041 && position < 1044) {
            mTitle.setText(R.string.Surah092);
        } else if (position >= 1044 && position < 1046) {
            mTitle.setText(R.string.Surah093);
        } else if (position >= 1046 && position < 1048) {
            mTitle.setText(R.string.Surah094);
        } else if (position >= 1048 && position < 1050) {
            mTitle.setText(R.string.Surah095);
        } else if (position >= 1050 && position < 1053) {
            mTitle.setText(R.string.Surah096);
        }else if (position >= 1053 && position < 1055) {
            mTitle.setText(R.string.Surah097);
        } else if (position >= 1055 && position < 1058) {
            mTitle.setText(R.string.Surah098);
        } else if (position >= 1058 && position < 1060) {
            mTitle.setText(R.string.Surah099);
        }else if (position >= 1060 && position < 1062) {
            mTitle.setText(R.string.Surah100);
        }else if (position >= 1062 && position < 1064) {
            mTitle.setText(R.string.Surah101);
        }else if (position >= 1064 && position < 1066) {
            mTitle.setText(R.string.Surah102);
        } else if (position >= 1066 && position < 1068) {
            mTitle.setText(R.string.Surah103);
        } else if (position >= 1068 && position < 1070) {
            mTitle.setText(R.string.Surah104);
        }else if (position >= 1070 && position < 1073) {
            mTitle.setText(R.string.Surah105);
        } else if (position >= 1073 && position < 1075) {
            mTitle.setText(R.string.Surah106);
        } else if (position >= 1075 && position < 1077) {
            mTitle.setText(R.string.Surah107);
        } else if (position >= 1077 && position < 1079) {
            mTitle.setText(R.string.Surah108);
        } else if (position >= 1079 && position < 1081) {
            mTitle.setText(R.string.Surah109);
        }else if (position >= 1081 && position < 1083) {
            mTitle.setText(R.string.Surah110);
        } else if (position >= 1083 && position < 1085) {
            mTitle.setText(R.string.Surah111);
        } else if (position >= 1085 && position < 1087) {
            mTitle.setText(R.string.Surah112);
        }else if (position >= 1087 && position < 1089) {
            mTitle.setText(R.string.Surah113);
        } else if (position >= 1089 && position < 1091) {
            mTitle.setText(R.string.Surah114);
        } else if (position >= 1091 && position < 1092) {
            mTitle.setText(R.string.Oracion);
        } else if (position >= 1092 && position < 1117) {
            mTitle.setText(R.string.Indice);
        } else if (position >= 1117) {
            mTitle.setText(R.string.Glosario);

        }
    }





    @Override
    public void onPageScrollStateChanged(int state) {

    }




    class TouchImageAdapter extends PagerAdapter {

        TouchImageView mImageView;
        TouchImageView image2;


        @Override
        public int getCount() {
            return mPdfRenderer.getPageCount();
        }

        public CharSequence getPageTitle(int position)
        {
            return (position+1)+" of "+mPdfRenderer.getPageCount();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            mImageView = new TouchImageView(container.getContext());
            if (mPdfRenderer.getPageCount() <= position) {
                return mImageView;
            }
            // Make sure to close the current page before opening another one.
            if (null != mCurrentPage) {
                mCurrentPage.close();
            }
            mCurrentPage = mPdfRenderer.openPage(position);

            // Important: the destination bitmap must be ARGB (not RGB).
            Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth() * 2, mCurrentPage.getHeight() * 2,
                    Bitmap.Config.ARGB_8888);
            // Here, we render the page onto the Bitmap.
            // To render a portion of the page, use the second and third parameter. Pass nulls to get
            // the default result.
            // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
            mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            // We are ready to show the Bitmap to user.
            mImageView.setImageBitmap(bitmap);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else {
                mImageView.setScaleType((ImageView.ScaleType.CENTER_CROP));}
            container.addView(mImageView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            mImageView.setTag(position);
            return mImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


    }



}



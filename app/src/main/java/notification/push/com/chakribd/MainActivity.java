package notification.push.com.chakribd;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import at.favre.lib.dali.builder.nav.DaliBlurDrawerToggle;
import at.favre.lib.dali.builder.nav.NavigationDrawerListener;
import notification.push.com.chakribd.Adapter.NaviListCustomAdapter;
import notification.push.com.chakribd.Database.DatabaseOperation;
import notification.push.com.chakribd.Fragment.FavouriteDialog;
import notification.push.com.chakribd.POJO.FavoutitePojo;
import notification.push.com.chakribd.POJO.NaviListPojo;

public class MainActivity extends AppCompatActivity {

    ListView circularList, otherList;
    NaviListCustomAdapter adapter, otherAdapter;
    List<NaviListPojo> pojoList, otherData;
    WebView webView;
    ScrollView mScroll;
    String currentTitle;
    int count = 0;
    String urlToLoad;
    SwipeRefreshLayout refreshLayout;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            //this is action event for floting action bar
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }else{
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        refreshLayout = findViewById(R.id.swiperefresh);

        refreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.loadUrl(webView.getUrl());
            }
        });

        DaliBlurDrawerToggle daliBlurDrawerToggle = new DaliBlurDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close, new NavigationDrawerListener() {

            @Override
            public void onDrawerClosed(View view) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }
        });

        drawer.addDrawerListener(daliBlurDrawerToggle);
        daliBlurDrawerToggle.syncState();

        circularList = findViewById(R.id.circular_list);
mScroll = findViewById(R.id.scroll);
        pojoList = new ArrayList<>();
        pojoList.add(new NaviListPojo("Home", R.drawable.ic_people_black_24dp));
        pojoList.add(new NaviListPojo("Teacher Job", R.drawable.ic_people_black_24dp));
        pojoList.add(new NaviListPojo("PartTime Job", R.drawable.ic_people_black_24dp));
        pojoList.add(new NaviListPojo("Govt Job", R.drawable.ic_people_black_24dp));
        pojoList.add(new NaviListPojo("ENGO Job", R.drawable.ic_people_black_24dp));
        pojoList.add(new NaviListPojo("Private Job", R.drawable.ic_people_black_24dp));
        pojoList.add(new NaviListPojo("Bank Job", R.drawable.ic_people_black_24dp));
        pojoList.add(new NaviListPojo("Defense Job", R.drawable.ic_people_black_24dp));

        adapter = new NaviListCustomAdapter(this, R.layout.nav_custom_list, pojoList);
        circularList.setAdapter(adapter);

        //this is the click listener for main list
        circularList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        //click action for home
                       urlToLoad = "https://pratidin24.blogspot.com/";
                       loadSite(urlToLoad);
                       drawer.closeDrawer(GravityCompat.START);
                       break;
                    case 1:
                        //click action for teacher job
                        urlToLoad = "http://chakriapp.blogspot.com/2018/01/blog-post_20.html";
                        loadSite(urlToLoad);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        //click action for parttime job
                        urlToLoad = "http://chakriapp.blogspot.com/2018/01/daily-star-editorial-14jan-2018.html";
                        loadSite(urlToLoad);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        //click action for govt job
                        urlToLoad = "http://chakriapp.blogspot.com/2018/03/blog-post_22.html";
                        loadSite(urlToLoad);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    //follow the list item order for other click action

                }
            }
        });
        count = 3;
        otherData = new ArrayList<>();
        otherData.add(new NaviListPojo("Favouite",R.drawable.ic_people_black_24dp));
        otherData.add(new NaviListPojo("Notification",R.drawable.ic_people_black_24dp));
        otherData.add(new NaviListPojo("About",R.drawable.ic_people_black_24dp));
        otherData.add(new NaviListPojo("Contacts",R.drawable.ic_people_black_24dp));

        otherList = findViewById(R.id.other_list);
        otherAdapter = new NaviListCustomAdapter(this,R.layout.nav_custom_list,otherData);
        otherList.setAdapter(otherAdapter);



        setListViewHeightBasedOnChildren(otherList);
        setListViewHeightBasedOnChildren(circularList);
        webView = findViewById(R.id.webview);


        String link = getIntent().getStringExtra("link");
        loadSite("https://pratidin24.blogspot.com/");

        if(getIntent()!=null){
            webView.loadUrl(link);
        }else {
            loadSite("https://pratidin24.blogspot.com/");
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(request.toString());
                currentTitle = view.getTitle();

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, final String url) {



                if(url.contains(".jpg") || url.contains(".png")){

                    if(Build.VERSION.SDK_INT >=23){

                        Context nContext = MainActivity.this.getApplicationContext();
                        if(nContext.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            MainActivity.this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            return true;
                        }
                    }

                    final AlertDialog.Builder dailog = new AlertDialog.Builder(MainActivity.this);
                    dailog.setMessage("Do you want to Download this Image?");
                    dailog.setIcon(R.mipmap.ic_launcher_round);
                    dailog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DownloadManager mdDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                            DownloadManager.Request requests = new DownloadManager.Request(
                                    Uri.parse(url));
                            File destinationFile = new File(
                                    Environment.getExternalStorageDirectory(),
                                    getFileName(url));
                            requests.setDescription("Downloading ...");
                            requests.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            requests.setDestinationUri(Uri.fromFile(destinationFile));
                            if (mdDownloadManager != null) {
                                mdDownloadManager.enqueue(requests);
                            }

                        }
                    });

                    dailog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dailog.show();




                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
                if(refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                dialog.dismiss();
                if(refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(false);
                }
            }

        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                currentTitle = title;
            }
        });

        //this is the item click listener for other list
        otherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    switch (i){

                        case 0:
                            //click action for favourite
                            FavouriteDialog fragment = new FavouriteDialog();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("data",(Serializable) DatabaseOperation.getFavourite(getApplicationContext()));
                            fragment.setArguments(bundle);
                            fragment.show(getSupportFragmentManager(),null);
                            break;
                        case 1:
                            //click action for notification
                            break;

                        case 2:
                            //click action for about

                            break;
                    }

            }
        });
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (webView.canGoBack()) {
          count--;
          if(count!=0){
              webView.goBack();
          }else{
              final AlertDialog.Builder dailog = new AlertDialog.Builder(this);
              dailog.setMessage("Do you want to exit?");
              dailog.setIcon(R.mipmap.ic_launcher_round);
              dailog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      moveTaskToBack(true);
                      android.os.Process.killProcess(android.os.Process.myPid());
                      System.exit(1);
                  }
              });

              dailog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                      count = 3;
                  }
              });
              dailog.show();
          }


        } else {
            final AlertDialog.Builder dailog = new AlertDialog.Builder(this);
            dailog.setMessage("Do you want to exit?");
            dailog.setIcon(R.mipmap.ic_launcher_round);
            dailog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });

            dailog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dailog.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void loadSite(String url){
        webView.loadUrl(url);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_fav) {
            if (webView.getUrl() == null || webView.getUrl().length() <= 0) {
                Toast.makeText(this, "Favourite not added",Toast.LENGTH_LONG).show();
            } else {
                if(currentTitle.contains("Web page not available")){
                    Toast.makeText(this, "Favourite not added",Toast.LENGTH_LONG).show();
                }else{
                    if(DatabaseOperation.InsertData(this,currentTitle, webView.getUrl())){
                        Toast.makeText(this, "Favourite added",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this, "Favourite not added",Toast.LENGTH_LONG).show();
                    }
                }

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ArrayAdapter listAdapter = (ArrayAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public String getFileName(String url) {
        String filenameWithoutExtension = "";
        filenameWithoutExtension = String.valueOf(System.currentTimeMillis()
                + ".jpg");
        return filenameWithoutExtension;
    }
}



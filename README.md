# testinglib

## menu
### menu layout
    <?xml version="1.0" encoding="utf-8"?>
    <menu xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">
        <item android:id="@+id/new_game"
            android:icon="@drawable/ic_launcher_background"
            android:title="new game"
            app:showAsAction="ifRoom"
            />
        <item android:id="@+id/help"
            android:icon="@drawable/ic_launcher_background"
            android:title="help"/>
    </menu>

we have 2 kind of menu
### popup menu
![Alt Text](https://github.com/azuredragon3000/testinglib/blob/master/image/menu.gif)
call popup menu in view listener  

    private void showPopup(View v) {
            PopupMenu popup = new PopupMenu(this, v);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.menu_example);
            popup.show();
            ;
        } 
        
        
### option menu

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_example,menu);
            return true;
        }
### implement listener for those menu
for popup menu we implement PopupMenu.OnMenuItemClickListener in activity  

    @SuppressLint({"NonConstantResourceId", "ShowToast"})
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.new_game:
                    Toast.makeText(this,"new game",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.help:
                    Toast.makeText(this,"help",Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.new_game:
                    Toast.makeText(this,"new game",Toast.LENGTH_LONG).show();
                   // archive(item);
                    return true;
                case R.id.help:
                    Toast.makeText(this,"help",Toast.LENGTH_LONG).show();
                   // delete(item);
                    return true;
                default:
                    return false;
            }
        }

## recycle view
to create Recycle view we have a Adapter  
### Adapter
  constructor to initialize object and take input data from parameter  
  onCreateViewHolder inflater item.xml from adapter  
  onBindViewHolder use holder to show data from item.xml  
  ViewHolder internal class to initial holder  
  getItemCount for adapter
  
          ArrayList<String> arrayList;
            public MenuAdapter(ArrayList<String> arrayList){
                    this.arrayList = arrayList;
            }
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
                return new ViewHolder(view);
            }
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                String t= arrayList.get(position);
                holder.tv.setText(t);
            }
            @Override
            public int getItemCount() {
                return arrayList.size();
            }
            public static class ViewHolder extends RecyclerView.ViewHolder{
                TextView tv;
                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    tv = itemView.findViewById(R.id.item_tv);
                }
            }

### set up recycle view  

    recyclerView = findViewById(R.id.rc);
    MenuAdapter myAdapter = new MenuAdapter(arrayList);
    recyclerView.setAdapter(myAdapter);
    LinearLayoutManager linearLayout = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayout);  
    
    
## login app
  we have some edittext for login purpose  
  we have some edittext for resigter purpose  
  we have to handle firebase register or login  
  we have to handle local database use room object  
  we indentify the device as a user in case noone register to tracking device
  
  how use firebase to register  
  register app with firebase project in web
  perform step by step which guilde by firebase server
  create variable as FirebaseAuth mAuth
  get instance ( we cant initialise this object, its should be singleton pattern
  
  create user with firebase  
  
      mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
      new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"User register successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(requireActivity(),MainActivity.class));
                        }else{
                            Toast.makeText(getContext(),"Register Error: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
       });
   login with firebase  
  
       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"User logged successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(requireActivity(),MainActivity.class));
                        }else{
                            Toast.makeText(getContext(),"Register Error: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
        });
  
  how to check editext is wrongly input  
  
        String email = et_email.getText().toString();
        String password = et_pass.getText().toString();
        if(TextUtils.isEmpty(email)){
            et_email.setError("Email cant be empty");
            et_email.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            et_pass.setError("Email cant be empty");
            et_pass.requestFocus();
  
  
## viewpage 2
![Alt Text](https://github.com/azuredragon3000/testinglib/blob/master/image/viewpage2.gif)
if you want to switch between 2 fragment using tab, viewpage2 is the best choose  
how to do it ?  
create FragmentStateAdapter

        public class LoginAdapter extends FragmentStateAdapter {
            private Context context;
            int totalTabs;
            public LoginAdapter(FragmentActivity fm, Context context, int totalTabs){
                super(fm);
                this.context = context;
                this.totalTabs = totalTabs;
            }
            @Override
            public int getItemCount() {
                return totalTabs;
            }

            public Fragment getItem(int position){
                switch (position){
                    case 0:
                        LoginTabFragment loginTabFragment
                                = new LoginTabFragment();
                        return loginTabFragment;
                    case 1:
                        SignupTabFragment signupTabFragment
                                = new SignupTabFragment();
                        return signupTabFragment;
                    default:
                        return null;
                }
            }
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment frament = getItem(position);
                return frament;
            }
        }

in mainactivity you create tabLayout and viewPage in xml file then in code you implements as below  


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final LoginAdapter adapter =
                new LoginAdapter(this,
                        this,
                        tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();

## animate and change theme
this is special source help you animate ball button  
![Alt Text](https://github.com/azuredragon3000/testinglib/blob/master/image/theme.gif)

layout  

        <?xml version="1.0" encoding="utf-8"?>
        <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".MainActivityTheme">

            <View
                android:id="@+id/holderbg"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.0"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.0" />

            <View
                android:id="@+id/dynamicbg"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:elevation="2dp"
                android:scaleX="0"
                android:scaleY="0"
                android:layout_marginBottom="0dp"
                android:layout_marginEnd="0dp"
                android:layout_marginStart="0dp"
                android:layout_marginTop="0dp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />

            <TextView
                android:id="@+id/textView"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginEnd="8dp"
                android:elevation="7dp"
                android:layout_marginStart="8dp"
                android:layout_marginTop="44dp"
                android:text="Choose Your Mood"
                android:textColor="#FFF"
                android:textSize="28sp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                />

            <TextView
                android:id="@+id/textView2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="8dp"
                android:layout_marginTop="16dp"
                android:layout_marginEnd="8dp"
                android:gravity="center_horizontal"
                android:elevation="7dp"
                android:lineSpacingExtra="6dp"
                android:text="Theme is still able to change \nunder in the setting  "
                android:textAlignment="center"
                android:textColor="#3c000000"
                android:textSize="18sp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.496"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/textView" />

            <androidx.appcompat.widget.AppCompatButton
                android:id="@+id/btngreen"
                android:layout_width="73dp"
                android:layout_height="80dp"
                android:layout_marginEnd="8dp"
                android:layout_marginStart="8dp"
                android:background="@drawable/btngreen"
                android:elevation="7dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/textView2" />

            <androidx.appcompat.widget.AppCompatButton
                android:id="@+id/btnblue"
                android:layout_width="73dp"
                android:layout_height="80dp"
                android:layout_marginEnd="8dp"
                android:layout_marginStart="8dp"
                android:background="@drawable/btnblue"
                android:elevation="7dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/btngreen" />

            <androidx.appcompat.widget.AppCompatButton
                android:id="@+id/btnorange"
                android:layout_width="73dp"
                android:layout_height="80dp"
                android:layout_marginEnd="8dp"
                android:layout_marginStart="8dp"
                android:background="@drawable/btnorange"
                android:elevation="7dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/btnblue" />

            <androidx.appcompat.widget.AppCompatButton
                android:id="@+id/btnpurple"
                android:layout_width="73dp"
                android:layout_height="80dp"
                android:layout_marginEnd="8dp"
                android:layout_marginStart="8dp"
                android:background="@drawable/btnred"
                android:elevation="7dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/btnorange" />

            <androidx.appcompat.widget.AppCompatButton
                android:id="@+id/btnsave"
                android:elevation="7dp"
                android:layout_width="200dp"
                android:layout_height="50dp"
                android:layout_marginBottom="52dp"
                android:layout_marginEnd="8dp"
                android:layout_marginStart="8dp"
                android:background="@drawable/bgwhite"
                android:text="Save Now"
                android:textAllCaps="false"
                android:textColor="#1abc9c"
                android:textSize="20sp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.497"
                app:layout_constraintStart_toStartOf="parent"
                />
        </androidx.constraintlayout.widget.ConstraintLayout>

source  

        public class MainActivityTheme extends AppCompatActivity {
            TextView textView1,textView2;
            Button btngreen,btnblue,btnpurple,btnorange, btnsave;
            View holderbg, dynamicbg;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activitytheme);
                btnsave = findViewById(R.id.btnsave);
                textView1 = findViewById(R.id.textView);
                textView2 = findViewById(R.id.textView2);
                btngreen = findViewById(R.id.btngreen);
                btnblue = findViewById(R.id.btnblue);
                btnpurple = findViewById(R.id.btnpurple);
                btnorange = findViewById(R.id.btnorange);
                holderbg = findViewById(R.id.holderbg);
                dynamicbg = findViewById(R.id.dynamicbg);
                holderbg.setBackgroundResource(R.drawable.bggreen);
                holderbg.setScaleX(3);
                holderbg.setScaleY(3);
                btngreen.setScaleY(1.5f);
                btngreen.setScaleX(1.5f);
                btnblue.setOnClickListener(v->{
                    btnblue.animate().translationY(20).scaleX(1.5f)
                            .scaleY(1.5f).setDuration(800).start();
                    btngreen.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    btnpurple.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    btnorange.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    dynamicbg.setBackgroundResource(R.drawable.bgblue);
                    dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();
                    btnsave.setTextColor(Color.parseColor("#3498db"));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holderbg.setScaleX(3);
                            holderbg.setScaleY(3);
                            holderbg.setBackgroundResource(R.drawable.bgblue);
                            dynamicbg.setScaleX(0);
                            dynamicbg.setScaleY(0);
                        }
                    },850);
                });
                btngreen.setOnClickListener(v->{
                    btngreen.animate().scaleX(1.5f)
                            .scaleY(1.5f).setDuration(800).start();
                    btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    btnpurple.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    btnorange.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    dynamicbg.setBackgroundResource(R.drawable.bggreen);
                    dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();
                    btnsave.setTextColor(Color.parseColor("#1bac9c"));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holderbg.setScaleX(3);
                            holderbg.setScaleY(3);
                            holderbg.setBackgroundResource(R.drawable.bggreen);
                            dynamicbg.setScaleX(0);
                            dynamicbg.setScaleY(0);
                        }
                    },850);
                });
                btnorange.setOnClickListener(v->{
                    btnorange.animate().scaleX(1.5f)
                            .scaleY(1.5f).setDuration(800).start();
                    btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    btnpurple.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    btngreen.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    dynamicbg.setBackgroundResource(R.drawable.bggorange);
                    dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();
                    btnsave.setTextColor(Color.parseColor("#1bac9c"));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holderbg.setScaleX(3);
                            holderbg.setScaleY(3);
                            holderbg.setBackgroundResource(R.drawable.bggorange);
                            dynamicbg.setScaleX(0);
                            dynamicbg.setScaleY(0);
                        }
                    },850);
                });
                btnpurple.setOnClickListener(v->{
                    btnpurple.animate().scaleX(1.5f)
                            .scaleY(1.5f).setDuration(800).start();
                    btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
                    btngreen.animate().scaleX(1).scaleY(1).setDuration(350).start();
                    btnorange.animate().scaleX(1).scaleY(1).setDuration(350).start();
                    dynamicbg.setBackgroundResource(R.drawable.bgpurple);
                    dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();
                    btnsave.setTextColor(Color.parseColor("#1bac9c"));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holderbg.setScaleX(3);
                            holderbg.setScaleY(3);
                            holderbg.setBackgroundResource(R.drawable.bgpurple);
                            dynamicbg.setScaleX(0);
                            dynamicbg.setScaleY(0);
                        }
                    },850);
                });
            }
        }


## asyn task + progressbar
![Alt Text](https://github.com/azuredragon3000/testinglib/blob/master/image/backgroundtask.gif)  
if you want to perfrom task in background and use progressbar to show the status please implement as below  
currently doInBackground is deprecated but i will update later  

insert progressbar in layout, but set it as invisible  

        <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            xmlns:wheel="http://schemas.android.com/apk/res-auto"
            android:orientation="vertical"
            tools:context=".MenuActivity">
            
            <com.pnikosis.materialishprogress.ProgressWheel
                android:id="@+id/progress"
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:layout_gravity="center"
                android:visibility="gone"
                wheel:matProg_barColor="@color/bright_blue"
                wheel:matProg_progressIndeterminate="true"
                />
            <androidx.recyclerview.widget.RecyclerView
                android:layout_marginTop="20dp"
                android:id="@+id/rc"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>

        </LinearLayout>

source  

        private RecyclerView recyclerView;
        protected ArrayList<String> arrayList;
        private ProgressWheel progressWheel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
            arrayList = new ArrayList<>();

            progressWheel = findViewById(R.id.progress);
            recyclerView = findViewById(R.id.rc);

            LinearLayoutManager linearLayout = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayout);

            TextView tv = findViewById(R.id.tv);
            tv.setOnClickListener(v->{
                showPopup(tv);
            });

            progressWheel.setVisibility(View.VISIBLE);
            new backgroundTask().execute();
        }

        class backgroundTask extends AsyncTask<Void,String,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                arrayList = new ArrayList<>();
                for(int i=0;i<10000;i++){
                    arrayList.add("this is item #"+i);
                    publishProgress(Double.toString((i*100)/5000));
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                progressWheel.setProgress(Float.parseFloat(values[0]));
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                MenuAdapter myAdapter = new MenuAdapter(arrayList);
                recyclerView.setAdapter(myAdapter);
                progressWheel.setVisibility(View.GONE);
            }
        }

remember import this import com.pnikosis.materialishprogress.ProgressWheel;  
in gradle implementation 'com.pnikosis:materialish-progress:1.7'  

# list view and RecycleView
  
  
Y??u c???u s??? d???ng ViewHolder Pattern trong Adapter: V???i ListView ch??ng ta kh??ng c???n s??? d???ng ViewHolder pattern ????? c???i thi???n performance c???a ListView nh??ng ng?????c l???i khi ch??ng ta t???o m???t Adapter s??? d???ng v???i RecyclerView b???t bu???c ph???i s??? d???ng ViewHolder ????? c???i thi???n performance.
  
M???c ????ch s??? d???ng ViewHolder ????? t??i s??? d???ng View nh???m tr??nh vi???c t???o View m???i v?? findViewById qu?? nhi???u
  
ListView ch??? support cho ch??ng ta danh s??ch d???ng scroll d???c. Nh??ng v???i RecylerView cung c???p cho ch??ng ta RecyclerView.LayoutManager cho ph??p Layout c??c item trong listView theo c??c ki???u kh??c nhau (ngang, d???c, d???ng grid, d???ng staggered grid).
  
V???i ListView vi???c x??? l?? animation cho c??c item trong ListView kh??ng h??? d??? d??ng. Nh??ng ?????i v???i RecyclerView c?? h??? tr??? ItemAnimator gi??p ch??ng ta c?? th??? x??? l?? animation khi add hay remove m???t item ra kh???i Recycler m???t c??ch d??? d??ng. M???c ?????nh RecyclerView s??? d???ng DefaulItemAnimtor.  
  
V???i ListView vi???c s??? d???ng divider kh??ng ???????c linh ho???t nh??ng v???i RecylerView c?? h??? tr??? ItemDecoration cho ph??p ch??ng ta draw divider m???t c??ch tu??? th??ch.
  
ListView c?? support c??c ph????ng th???c ph????ng th???c setOnItemClickListener v?? setOnLongItemListener ????? ch???n 1 item trong ListView. Nh??ng RecylerView ch??? support m???t ph????ng th???c ???? l?? onItemTouchListener.  

## C??c th??nh ph???n khi s??? d???ng RecyclerView 

C??ng gi???ng nh?? ListView th?? ????y l?? th??nh ph???n x??? l?? data collecion (d??? li???u ki???u danh s??ch) v?? bind (g???n) nh???ng d??? li???u n??y l??n c??c Item c???a RecyclerView.
  
Khi t???o custom Adapter ch??ng ta ph???i override l???i hai ph????ng th???c ch??nh l??
  
**onCreateViewHolder:**Ph????ng th???c d??ng ????? t???o view m???i cho RecyclerView. N???u RecyclerView ???? cached l???i View th?? ph????ng th???c n??y s??? kh??ng g???i.
  
onBindViewHolder: Ph????ng th???c n??y d??ng ????? g???n data v?? view  
  
LayoutManager  
L?? th??nh ph???n c?? ch???c n??ng s???p x???p c??c item trong RecylerView. C??c item scroll d???c hay ngang ph??? thu???c ch??ng ta set LayoutManager n??y cho RecyclerView.  
C??c class con c???a LayoutManager:  
L?? th??nh ph???n c?? ch???c n??ng s???p x???p c??c item trong RecylerView. C??c item scroll d???c hay ngang ph??? thu???c ch??ng ta set LayoutManager n??y cho RecyclerView.  
C??c class con c???a LayoutManager: LinenarLayoutManager: H??? tr??? scroll c??c item theo chi???u ngang hay chi???u d???c.  
GridLayoutManager: Layout c??c item trong RecyclerView d?????i d???ng Grid gi???ng nh?? khi ch??ng ta s??? d???ng GridView.  
StaggerdGridLayoutManager: Layout c?? item trong ListView d?????i d???ng Grid so le.  
ItemAnimator: L?? th??nh ph???n h??? tr??? animation khi ch??ng ta add hay remove m???t item ra kh???i RecyclerView. T??i s??? h?????ng d???n chi ti???t v??? ItemAnimator trong b??i vi???t sau. ????? t??m hi???u r?? ph???n n??y t??i g???i ?? cho c??c b???n l?? n??n t??m hi???u c??c class sau  
ItemAnimator: L?? class ?????i di???n, khung s?????n c???a animation trong RecyclerView.  
SimpleItemAnimator: class wrapper l???i ItemAnimator.  
DefaultItemAnimtor: class x??? l?? animtion m???c ?????nh s??? d???ng trong RecyclerView.  

# base apdater and array adapter in android  
  BaseAdapter is abstract while ArrayAdapter extends BaseAdapter ( it is concrete implementation of a baseadapter)  
  BaseAdapter is a very generic adapter that allows you to do pretty much whatever you want, however, you have to do a bit  
  more coding yourself to get it working  
  ArrayAdapter is more complete implementation that works well for data in arrays or ArrayList, similarly there is a related CursorAdapter that you should user if your data is in a Cursor, both of these extend BaseAdapter  
# GridView
  
  in android Gridview is a view groupd that display items in 2 dimentional scrolling grid ( rows and columns)  
  note in grid view: numColumns property has to be specified otherwise gridview behaves like a listview with just singlechoise, if we set it to auto_fit then it automatically display as many column as possible to fill the available space of the screen.  
  
  we can use recycleview to create gridview also just implement as below  
  
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
  
# different between listview and recycleview.  
  
The main difference between ListView and GridView is how it lays out its child. With ListView you are laying your children one by one either vertically or horizontally only. With GridView, its a combination of both. It lays its children horizontally first.  
  
# different between listview and GridView.  

RecyclerView was created as a ListView improvement, so yes, you can create an attached list with ListView control, but using RecyclerView is easier as it:  
  
Reuses cells while scrolling up/down - this is possible with implementing View Holder in the ListView adapter, but it was an optional thing, while in the RecycleView it's the default way of writing adapter.  
  
Decouples list from its container - so you can put list items easily at run time in the different containers (linearLayout, gridLayout) with setting LayoutManager.  
  
Example:  
  
    mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    //or
    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    Animates common list actions - Animations are decoupled and delegated to ItemAnimator.
    There is more about RecyclerView, but I think these points are the main ones.
  
So, to conclude, RecyclerView is a more flexible control for handling "list data" that follows patterns of delegation of concerns and leaves for itself only one task - recycling items.

# RecycleView GridView ListView -- onClickListener
  for gridview its simple put this snapcode in MainActivity  
  

          gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // set an Intent to Another Activity
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("image", logos[position]); // put image data in Intent
                        startActivity(intent); // start Intent
                    }
                });
   
   
   but for Recycle view we have to implement more complex  
   first we create interface  
   
     
          public interface ClickListener {

            public void click(int index);
        }
        
          
  
  create this interface and pass it to Adapter of Recycle view  
  
  in MainActivity implement listener  
  
      ClickListener listener = new ClickListener() {
                @Override
                public void click(int index) {
                    Toast.makeText(getApplicationContext(), "clicked item index is " + index, Toast.LENGTH_LONG).show();
                }
            };
            
pass to Adapter

    AdapterRecycleView customAdapter2 = new AdapterRecycleView(this,birdList,listener);      
      
 in Adapter implement OnclickListener
 
 
 
              @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

                final int index = holder.getAdapterPosition();
                holder.tv.setText(items.get(position).getId());
                holder.im.setImageResource(items.get(position).getLogo());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.click(index);
                    }
                });
            }
            
# understand about listener 
  
  actually listener mainly use interface concept, we can image object A has 3 function f1,f2,f3, if you want to call f1 or f2 from other object we can using interface and we can call it anytime object B want, we can call this with other word is listener
 
         Interface listener{
            click();
         }

         class A implement listener{


            // second way
            listener C = new listener(){
                public void click(){
                    // perform something
                }
            }

            A(){
                new B = B(this,C);
            }


            func1()
            func2()
            func3()

            click(){
                // perform something
            }
         }

         class B{

         listener l;
          listener l2;
            B(listtener A, listener C){
                l = A;
                l2= C;
            }

            funcB(){
                l.click();
                l2.click();
            }
         }

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



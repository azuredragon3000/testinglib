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

## animate and change theme

## asyn task + progressbar



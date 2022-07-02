# testinglib

## recycle view
to create Recycle view we have a Adapter  
### Adapter
  constructor to initialize object and take input data from parameter  
  onCreateViewHolder inflater item.xml from adapter  
  onBindViewHolder use holder to show data from item.xml  
  ViewHolder internal class to initial holder  
  
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

## fire base

## asyn task + progressbar



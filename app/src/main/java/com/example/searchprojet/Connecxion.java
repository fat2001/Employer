package com.example.searchprojet;

import static com.example.searchprojet.Activity_annonce.recyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Connecxion {
    FirebaseFirestore fr = FirebaseFirestore.getInstance();

    public void Login(String email, String password, Context context){
        if (fr!=null){
            CollectionReference usersRef = fr.collection("data").document("user").collection("users");
            Query query = usersRef.whereEqualTo("email", email)
                    .whereEqualTo("password", password).limit(1);
            ProgressDialog dialog = new ProgressDialog(context);
            dialog.setMessage("Loading...");
            dialog.show();
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        User user = new User();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot.isEmpty()) {
                            Toast.makeText(context, "Utilisateur n'existe pas", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                            String userId = documentSnapshot.getId();
                            String usernom = documentSnapshot.get("nom").toString();
                            user.setIdUser(Integer.valueOf(userId));
                            user.setNom(usernom);
                            Intent intent = new Intent(context,Activity_annonce.class);
                            intent.putExtra("user",user);
                            context.startActivity(intent);
                        }
                        dialog.dismiss();



                    }
                }

            });



        }
    }

    public void addActivite(int id,Activite act) {


        fr.collection("data").document("user").collection("users").document(String.valueOf(id)).collection("activite").document(String.valueOf(act.getIdActivite())).set(act);

    }


    public void adduser(User us) {

        try {
            if (fr != null) {
                DocumentReference utilisateur= fr.collection("data").document("user").collection("users").document(String.valueOf(us.getIdUser())) ;
                utilisateur.set(us).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("rahtzad","fffffff");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("rahtzad",e.getMessage());

                    }
                });
                //DocumentReference docref = fr.collection("data").document("user").collection("users").document(String.valueOf(us.getIdUser()));
                // ApiFuture<WriteResult> result = docref.set(us);
                // System.out.println("" + result.get().getUpdateTime());
                //  System.out.println("Successful");
            } else {
                System.out.println("our attempt is failed");
            }
        } catch (Exception ex) {
            // Handle the exception
        }
    }
    public void getallactivity (Context context){
        ArrayList<Activite> activites = new ArrayList<>();
        Anonce_adapter adapter = new Anonce_adapter(activites, context);

        fr.collection("data").document("user").collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot document: queryDocumentSnapshots) {
                    fr.collection("data").document("user").collection("users")
                            .document(document.getId()).collection("activite").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    //  Activite activite = new Activite()
                                    if (queryDocumentSnapshots!=null){

                                        for (QueryDocumentSnapshot documentactivite: queryDocumentSnapshots) {
                                            if (documentactivite.get("date") != null && documentactivite.get("sujet") != null && documentactivite.get("lieu") != null) {

                                                Activite activite = new Activite(documentactivite.get("date").toString(), documentactivite.get("sujet").toString(), documentactivite.get("lieu").toString());
                                                    activite.setNomDesc(documentactivite.get("desc").toString());
                                                activites.add(activite);
                                                adapter.notifyDataSetChanged();
                                                Log.d("anchof hadchi ",activite.toString());
                                            }
                                        }
                                    }

                                }
                            });

                }
                recyclerView.setAdapter(adapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    };

    public void getmesactivity (int id,Context context){
        ArrayList<Activite> activites = new ArrayList<>();
        Anonce_adapter adapter = new Anonce_adapter(activites, context);


        fr.collection("data").document("user").collection("users")
                .document(String.valueOf(id)).collection("activite").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                                                    @Override
                                                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                                                        //  Activite activite = new Activite()
                                                                                                        if (queryDocumentSnapshots!=null){
                                                                                                            for (QueryDocumentSnapshot documentactivite: queryDocumentSnapshots) {
                                                                                                                if (documentactivite.get("date") != null && documentactivite.get("nomActivite") != null && documentactivite.get("lieu") != null) {

                                                                                                                    Activite activite = new Activite(documentactivite.get("date").toString(), documentactivite.get("nomActivite").toString(), documentactivite.get("lieu").toString());
                                                                                                                    activites.add(activite);
                                                                                                                    adapter.notifyDataSetChanged();
                                                                                                                    Log.d("anchof hadchi ",activite.toString());
                                                                                                                }
                                                                                                            }
                                                                                                        }

                                                                                                    }


                                                                                                }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        recyclerView.setAdapter(adapter);


    };



    public void updatUser(int id, String nom, int telephone, String password) {
/*
        try {
            if (fr != null) {
                DocumentReference docref = fr.collection("data").document("user").collection("users").document(String.valueOf(id));
                ApiFuture<WriteResult> result = (ApiFuture<WriteResult>) docref.update("nom", nom, "telephone", telephone, "password", password);
                System.out.println("" + result.get().getUpdateTime());
                System.out.println("Successful");
            } else {
                System.out.println("our attempt is failed");
            }
        } catch (InterruptedException | ExecutionException ex) {
            // Handle the exception
        }
    }
 */
    }
/*
    public void addservice(service n){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("service").collection("serv").document(String.valueOf(n.id));
                ApiFuture<WriteResult> result = (ApiFuture<WriteResult>) docref.set(n);
                System.out.println(""+result.get().getUpdateTime());
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
        //  DocumentReference docref =fr.collection("data").document("service").collection("serv").document("hadk");
        //              ApiFuture<WriteResult> result =docref.set(se);
    }
    public void addactivite(int id,Activite ac){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("user").collection("users").document(String.valueOf(id));
                // ApiFuture<WriteResult> result =docref.set(ac);
                //   DocumentReference docref.collection("activite").document("jamal");
                ApiFuture<WriteResult> res = (ApiFuture<WriteResult>) docref.collection("activite").document(String.valueOf(ac.getIdActivite())).set(ac);
                System.out.println(""+res.get().getUpdateTime());
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
    }
    public void addraport(int id,rapport ra){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("user").collection("users").document(String.valueOf(id));
                // ApiFuture<WriteResult> result =docref.set(ac);
                //   DocumentReference docref.collection("activite").document("jamal");
                ApiFuture<WriteResult> res = (ApiFuture<WriteResult>) docref.collection("rapports").document(String.valueOf(ra.getIdActivite())).set(ra);
                System.out.println(""+res.get().getUpdateTime());
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
    }
    public void addimagesAn(int iduser,int idact,Images im){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("user").collection("users").document(String.valueOf(iduser));
                // ApiFuture<WriteResult> result =docref.set(ac);
                //   DocumentReference docref.collection("activite").document("jamal");
                ApiFuture<WriteResult> res = (ApiFuture<WriteResult>) docref.collection("activite").document(String.valueOf(idact))
                        .collection("images").document(String.valueOf(im.getid())).set(im);
                System.out.println(""+res.get().getUpdateTime());
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
    }
    public void addimagesRa(int iduser,int idact,Images im){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("user").collection("users").document(String.valueOf(iduser));
                // ApiFuture<WriteResult> result =docref.set(ac);
                //   DocumentReference docref.collection("activite").document("jamal");
                ApiFuture<WriteResult> res = (ApiFuture<WriteResult>) docref.collection("rapports").document(String.valueOf(idact))
                        .collection("images").document(String.valueOf(im.getid())).set(im);
                System.out.println(""+res.get().getUpdateTime());
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
    }
    public void updateservice(int id,String n){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("service").collection("serv").document(String.valueOf(id));
                ApiFuture<WriteResult> result = (ApiFuture<WriteResult>) docref.update("name", n);
                System.out.println(""+result.get().getUpdateTime());
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
        //  DocumentReference docref =fr.collection("data").document("service").collection("serv").document("hadk");
        //              ApiFuture<WriteResult> result =docref.set(se);
    }
    public void updatactivite(int iduser, int idac, String sujetup, String lieu, String description, Date da, Images img){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("user").collection("users").document(String.valueOf(iduser));
                // ApiFuture<WriteResult> result =docref.set(ac);
                //   DocumentReference docref.collection("activite").document("jamal");
                ApiFuture<WriteResult> res = (ApiFuture<WriteResult>) docref.collection("activite").document(String.valueOf(idac)).update("lieu", lieu,"sujet",sujetup,"nomActivite",description,"Date",da);
                System.out.println(""+res.get().getUpdateTime());
//
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
    }
    public void updatrapport(int iduser,int idac,String sujetup,String lieu,String description,Date da,String pdf,Images img){
        try{
            if(fr != null){
                DocumentReference docref =fr.collection("data").document("user").collection("users").document(String.valueOf(iduser));
                // ApiFuture<WriteResult> result =docref.set(ac);
                //   DocumentReference docref.collection("activite").document("jamal");
                ApiFuture<WriteResult> res = (ApiFuture<WriteResult>) docref.collection("activite").document(String.valueOf(idac)).update("lieu", lieu,"sujet",sujetup,"nomActivite",description,"Date",da);
                System.out.println(""+res.get().getUpdateTime());
//
                System.out.println("Successful");
            }
            else{
                System.out.println("our attempt is failed");
            }
        }catch(InterruptedException | ExecutionException ex){
        }
    }
    public void voirActivite() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = (ApiFuture<QuerySnapshot>) fr.collection("data").document("user").collection("users").get();
// future.get() blocks on response
        List<DocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId());
            ApiFuture<QuerySnapshot> fu = (ApiFuture<QuerySnapshot>) fr.collection("data").document("user").collection("users")
                    .document(document.getId()).collection("activite").get();
            System.out.println(document.getId());
            List<DocumentSnapshot> docu = fu.get().getDocuments();
            for (DocumentSnapshot d : docu) {
                System.out.println(d.getId()+"--**--"+ d.getString("sujet"));
            }
        }
    }
    public void MesActivite(int iduser) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> fu = (ApiFuture<QuerySnapshot>) fr.collection("data").document("user").collection("users")
                .document(String.valueOf(iduser)).collection("activite").get();
        List<DocumentSnapshot> docu = fu.get().getDocuments();
        for (DocumentSnapshot d : docu) {
            System.out.println(d.getId()+"--**--"+ d.getString("sujet"));
        }
    }
    public void voirRapport() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = (ApiFuture<QuerySnapshot>) fr.collection("data").document("user").collection("users").get();
// future.get() blocks on response
        List<DocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId());
            ApiFuture<QuerySnapshot> fu = (ApiFuture<QuerySnapshot>) fr.collection("data").document("user").collection("users")
                    .document(document.getId()).collection("rapports").get();
            System.out.println(document.getId());
            List<DocumentSnapshot> docu = fu.get().getDocuments();
            for (DocumentSnapshot d : docu) {
                System.out.println(d.getId());
            }
        }
    }
    public void MesRapports(int iduser) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> fu = (ApiFuture<QuerySnapshot>) fr.collection("data").document("user").collection("users")
                .document(String.valueOf(iduser)).collection("rapports").get();
        List<DocumentSnapshot> docu = fu.get().getDocuments();
        for (DocumentSnapshot d : docu) {
            System.out.println(d.getId()+"--**--"+ d.getString("sujet"));
        }
    }
 */
}
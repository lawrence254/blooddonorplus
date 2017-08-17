package layout;


import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lawrence.blooddonor.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bloodBanks.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bloodBanks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bloodBanks extends Fragment {
    private RecyclerView recylerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;
    private List<MyData> data_List = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private static final String TAG="Adapter";

       public bloodBanks() {

        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bloodBanks.
     */
    // TODO: Rename and change types and number of parameters
    public static bloodBanks newInstance(String param1, String param2) {
        bloodBanks fragment = new bloodBanks();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_blood_banks, container, false);
        //perform(v);
        recylerView=(RecyclerView) v.findViewById(R.id.recyle);
        recylerView.setHasFixedSize(true);
//        data_List = new ArrayList<>();

        load_data_from_server(0);

//        adapter=new MyAdapter(getActivity(),data_List);
//
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recylerView.setLayoutManager(linearLayoutManager);
//        recylerView.setAdapter(adapter);

        return v;
    }

    private void load_data_from_server(final int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            private Context applicationContext;

            public Context getApplicationContext() {
                return applicationContext;
            }

            private Context activity;

            public Context getActivity() {
                return activity;
            }

            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client= new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.137.1:81/card.php?id="+id)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        MyData data=new MyData(object.getInt("id"),object.getString("hospName"),object.getString("contact"));
                        data_List.add(data);
                    }

                } catch(IOException e){
                    e.printStackTrace();
                }catch (JSONException e){
                    System.out.println("No More Hospitals");
                }
                Log.e(TAG, "I'm not running");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

               MyAdapter adapter = new MyAdapter(this.getActivity(), data_List);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recylerView.setLayoutManager(layoutManager);
                recylerView.setItemAnimator(new DefaultItemAnimator());
                recylerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        };
        task.execute(id);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

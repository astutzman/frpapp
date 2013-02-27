package phillykeyspots.frpapp;

import java.util.List;

import phillykeyspots.frpapp.XmlParser.Entry;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KeyspotInfoFragment extends Fragment {

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_keyspot_info, container, false);
	}
	
	public void onResume(){
		super.onResume();
		updateInfo();
	}
	
	private void updateInfo(){
		List<Entry> entries = DashboardActivity.keyspots.getEntries();
		Entry entry = null;
		for(Entry list: entries){
			if(list.keyspot.equals(((KeyspotActivity)getActivity()).info)){
				entry = list;
			}
		}
		String[] info = {"Name: "+entry.keyspot, 
				"Managin Partner: "+entry.managing_partner,
				"Contact: "+entry.contact,
				"City: "+entry.city,
				"Zip Code: "+entry.postal_code,
				"Address: "+entry.street,
				"Hours: "+entry.hours,
				"Workstations: "+entry.workstations,
				"Restrictions: "+entry.restrictions,
				"Wi-Fi: "+entry.wi_fi,
				"Latitude: "+entry.latitude,
				"Longitude: "+entry.longitude};
		LinearLayout layout = (LinearLayout)getActivity().findViewById(R.id.keyspot_info);
		for(String line: info){
			TextView temp = new TextView(getActivity());
			temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
			temp.setText(line);
			layout.addView(temp);
		}
	}
}
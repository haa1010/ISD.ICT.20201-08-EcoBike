package subsystem.interbank;

import utils.API;

/***
 * this class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author Tran Thi Hang
 *
 */
public class InterbankBoundary {

	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			throw new UnrecognizedException();
		}
		return response;
	}

}

package de.fliegersoftware.amazon.payment.ipn.impl;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;

import com.amazonservices.mws.offamazonpaymentsipn.NotificationsException;
import com.amazonservices.mws.offamazonpaymentsipn.validators.jca.IJCAAdapter;

public class CustomJCAAdapterBouncyCastleImpl implements IJCAAdapter {

	@Override
	public List<String> getCNNamesForCert(X509Certificate cert)
			throws NotificationsException {
		RDN[] rdns = getCNNames(cert);
		return convertToStringList(rdns);
	}

	private RDN[] getCNNames(X509Certificate cert) {
		X500Principal principal = cert.getSubjectX500Principal();
		X500Name x500name = new X500Name(principal.getName());
		return x500name.getRDNs(BCStyle.CN);
	}

	private List<String> convertToStringList(RDN[] rdns) {
		List<String> localArrayList = new ArrayList<String>(rdns.length);
		for (RDN rdn : rdns) {
			localArrayList.add(IETFUtils.valueToString(rdn.getFirst()
					.getValue()));
		}
		return localArrayList;
	}
}
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Test console</title>
</head>

<body>
	<h1>PayPal order accepted</h1>
	<h2>Order details</h2>
	<div>Todo: add order details here</div>
	<h2>Parameters received:</h2>
	<div>
		guid=${param.guid}<br /> token=${param.token}<br />
		PayerID=${param.PayerID}<br />
	</div>
	<a
		href="/paypal/test/console?guid=${param.guid}&token=${param.token}&PayerID=${param.PayerID}">Return
		to console</a>
</body>
</html>

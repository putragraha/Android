<?php  
include 'config.php';

$json = file_get_contents('php://input');
$obj = json_decode($json);

// To put image to path
$imageData = $obj->{'image'};
$imageName = $obj->{'name'}.".jpg";
$imagePath = "uploads/";
$imageURL = "$imagePath"."$imageName";

//Insert Query
$result = mysqli_query($conn, "INSERT INTO `imageuploader`.`imagetable` (image)
VALUES ('$imageURL')");

$conn->close();

  if($result) {
	// insert image to server
	file_put_contents($imagePath.$imageName,base64_decode($imageData));
	
	echo json_encode(array('posts'=>array("Success"))); 
  } else {
	echo json_encode(array('posts'=>array("Fail"))); 
  }

  ?>
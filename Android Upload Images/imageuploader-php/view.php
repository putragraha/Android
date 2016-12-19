<?php
include 'config.php';

$return_arr = array();

$fetch = mysqli_query($conn, "SELECT * FROM imagetable");
if ($fetch->num_rows > 0) {
	while($row = mysqli_fetch_assoc($fetch)) {
		array_push($return_arr, array('image'=>$row['image']));
	}
} else {
	array_push($return_arr, 'No Data');
}

$conn->close();

echo json_encode(array('image' => $return_arr));
?>  
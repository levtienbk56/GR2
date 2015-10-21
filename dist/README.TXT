CALCULATE STAYPOINT PROGRAM

- input: 
	+ input file: tệp .txt lưu trữ list các tọa độ gps của người dùng
		format: định dạng file: mỗi dòng 1 đọa độ: <id>,<date time>,<longitude>,<latitude>
	+ disthresh: phạm vi (đường kính) tối đa của 1 staypoint
	+ timethresh: khoảng tgian tối thiểu ở 1 stay point
	+ output name: tên file đầu ra
- output: 
	+ output process: hiển thị quá trình tính toán
	+ output file: tệp kết quả, lưu ngay tại thư mục của chương trình
		format: như input file, mỗi dòng là tọa độ trọng tâm của stay point
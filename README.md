# CSVExchanger🌱

`CSVExchanger` - allows users to upload file with `.csv` extention and store it by each line into H2 DB (Or any another DB you want) and then easily get data from DB.

## How it works?

![photo](https://ltdfoto.ru/images/2022/09/07/IZOBRAZENIE_2022-09-07_201112480.png)

## Usage

To start sending images via application - start a `Postman` and write `localhost:8080/upload` then need to fill a request param named as `file` which required for uploading any image you want. To get back uploaded data write `localhost:8080/all` to get all lines from uploaded file in DB.

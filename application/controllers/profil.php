<?php
defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

    class User extends REST_Controller{
        function all_get(){
            $get_kategori = $this->db->query("
            SELECT 
                kategori,
                photo_url
                FROM kategori")->result();
                $this->response(
                    array(
                        "status"=> "success", 
                        "result" =>$get_kategori
                    )
                    );
        }
    }
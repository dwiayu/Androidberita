<?php
defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

    class User extends REST_Controller{
        function all_get(){
            $get_kategori = $this->db->query("
            SELECT 
           br.isi, kat.kategori,kat.photo_url ,sumb.nama_sumber FROM berita br JOIN 
            kategori kat ON br.id_kategori=kat.id_kategori JOIN sumber sumb ON br.id_sumber=sumb.id_sumber ")->result();
                $this->response(
                    array(
                        "status"=> "success", 
                        "result" =>$get_kategori
                    )
                    );
        }
    }
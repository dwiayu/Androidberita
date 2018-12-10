<?php

defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . '/libraries/Format.php';

    class Userp extends REST_Controller{
        private $folder_upload='uploads/';
        function all_get(){
            $get_user = $this->db->query("
            SELECT
              id_user,nama, alamat, email,photo_url FROM user ")->result();
                $this->response(
                    array(
                        "status"=> "success", 
                        "result" =>$get_user
                    )
                    );
        }
        function all_post(){
            $action = $this->post('action');
            $data_user = array(
                'id_user' =>$this->post('id_user'),
                'nama' => $this->post('nama'),
                'alamat' => $this->post('alamat'),
                'email' =>$this->post('email'),
                'photo_url' =>$this->post('photo_url')
            );
            switch ($action){
                case 'insert';
                $this->insertUser($data_user);
                break;
    
                case 'update': 
                $this->updateUser($data_user);
                break; 
    
                default: 
                $this->response (
                    array(
                        "status" =>"failed", 
                        "message" =>"action harus diisi"
                    )
                    );
                    break;
            }
        }
        function insertUser($data_user){
            
                    
                // Cek validasi
                if (empty($data_user['nama']) || empty($data_user['alamat']) || empty($data_user['email'])){
                    $this->response(
                        array(
                            "status" => "failed",
                            "message" => "Data tidak lengkap"
                        )
                    );
                } else {
        
                    $data_user['photo_url'] = $this->uploadPhoto();
        
                    $do_insert = $this->db->insert('user', $data_user);
                   
                    if ($do_insert){
                        $this->response(
                            array(
                                "status" => "success",
                                "result" => array($data_user),
                                "message" => $do_insert
                            )
                        );
                    }
                }
                }
                function updateUser($data_user){
                    if(empty($data_user['nama']) || empty($data_user['alamat']) ||
                    empty($data_user['email'])){
                        $this->response(
                            array(
                                "status" =>"failed",
                                "message" => "Lengkapi Data"             
                        )
                            );
                    }else {
                        $get_user_baseID= $this->db->query("
                        SELECT 1
                        FROM user WHERE id_user = {$data_user['id_user']}")->num_rows();
            
                        if ($get_user_baseID ==0){
                            $this->response(
                                array(
                                    "status" => "failed",
                                    "message" =>"ID user tidak ditemukan"
                                )
                                );
                        }else{
                            $data_user['photo_url'] = $this->uploadPhoto();
                            if($data_user['photo_url']){
                                $update = $this->db->query(" 
                                UPDATE user SET 
                                nama = '{$data_user['nama']}',
                                alamat = '{$data_user['alamat']}',
                                email = '{$data_user['email']}',
                                photo_url = '{$data_user['photo_url']}'
                                WHERE id_user = '{$data_user['id_user']}'");
                            }else{
                                $update = $this->db->query("
                                UPDATE user
                                SET
                                    nama = '{$data_user['nama']}',
                                    alamat ='{$data_user['alamat']}',
                                    email = '{$data_user['email']}'
                                WHERE id_user = {$data_user['id_user']}"
                            );
                            }
                            if($update){
                                $this->response(
                                    array(
                                        "status" => "success", 
                                        "result" => array ($data_user),
                                        "message" =>$update
                                    )
                                    );
                            }
                        }
                    }
                } 
                
                function uploadPhoto() {
                    
                            // Apakah user upload gambar?
                            if ( isset($_FILES['photo_url']) && $_FILES['photo_url']['size'] > 0 ){
                                
                                            // Foto disimpan di android-api/uploads
                                            $config['upload_path'] = realpath(FCPATH . $this->folder_upload);
                                            $config['allowed_types'] = 'jpg|png';
                                
                                            // Load library upload & helper
                                            $this->load->library('upload', $config);
                                            $this->load->helper('url');
                                
                                            // Apakah file berhasil diupload?
                                            if ( $this->upload->do_upload('photo_url')) {
                                
                                               // Berhasil, simpan nama file-nya
                                               // URL image yang disimpan adalah http://localhost/android-api/uploads/namafile
                                               $img_data = $this->upload->data();
                                               $post_image = base_url(). $this->folder_upload .$img_data['file_name'];
                                
                                            } else {
                                
                                                // Upload gagal, beri nama image dengan errornya
                                                // Ini bodoh, tapi efektif
                                                $post_image = $this->upload->display_errors();
                                                
                                            }
                                        } else {
                                            // Tidak ada file yang di-upload, kosongkan nama image-nya
                                            $post_image = '';
                                        }
                                
                                        return $post_image;
                                    }
            

    }
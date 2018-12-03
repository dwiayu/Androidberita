<?php

defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

    class Kategori extends REST_Controller{
        private $folder_upload='uploads/';
        function all_get(){
            $get_kategori = $this->db->query("
            SELECT
                id_kategori, 
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
        function all_post(){
            $action = $this->post('action');
            $data_kategori = array(
                'id_kategori' =>$this->post('id_kategori'),
                'kategori' => $this->post('kategori'),
                'photo_url' => $this->post('photo_url')
            );
            switch ($action){
                case 'insert';
                $this->insertKategori($data_kategori);
                break;
    
                case 'update': 
                $this->updateKategori($data_kategori);
                break; 
    
                case 'delete' : 
                $this->deleteKategori($data_kategori);
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
        function insertKategori($data_kategori){
            
                    // Cek validasi
                    if (empty($data_kategori['kategori'])){
                        $this->response(
                            array(
                                "status" => "failed",
                                "message" => "Kategori  harus diisi"
                            )
                        );
                    } else {
            
                        $data_kategori['photo_url'] = $this->uploadPhoto();
            
                        $do_insert = $this->db->insert('kategori', $data_kategori);
                       
                        if ($do_insert){
                            $this->response(
                                array(
                                    "status" => "success",
                                    "result" => array($data_kategori),
                                    "message" => $do_insert
                                )
                            );
                        }
                    }
                }
                function updateKategori($data_kategori){
                    if(empty($data_kategori['kategori'])){
                        $this->response(
                            array(
                                "status" =>"failed",
                                "message" => "kategori harus diisi"             
                        )
                            );
                    }else {
                        $get_kategori_baseID= $this->db->query("
                        SELECT 1
                        FROM kategori WHERE id_kategori = {$data_kategori['id_kategori']}")->num_rows();
            
                        if ($get_kategori_baseID ==0){
                            $this->response(
                                array(
                                    "status" => "failed",
                                    "message" =>"ID kategori tidak ditemukan"
                                )
                                );
                        }else{
                            $data_kategori['photo_url'] = $this->uploadPhoto();
                            if($data_kategori['photo_url']){
                                $update = $this->db->query(" 
                                UPDATE kategori SET 
                                kategori = '{$data_kategori['kategori']}',
                                photo_url = '{$data_kategori['photo_url']}'
                                WHERE id_kategori = '{$data_kategori['id_kategori']}'");
                            }else{
                                $update = $this->db->query("
                                UPDATE kategori
                                SET
                                    kategori = '{$data_kategori['kategori']}'
                                WHERE id_kategori = {$data_kategori['id_kategori']}"
                            );
                            }
                            if($update){
                                $this->response(
                                    array(
                                        "status" => "success", 
                                        "result" => array ($data_kategori),
                                        "message" =>$update
                                    )
                                    );
                            }
                        }
                    }
                } 
                function deleteKategori($data_kategori){
                    if (empty($data_kategori['id_kategori'])){
                        $this->response(
                            array(
                                "status" =>"failed",
                                "message" =>"ID kategori harus diisi"
                            )
                            );
                    }else{
                        $get_kategori_baseID = $this->db->query("
                        SELECT 1
                        FROM kategori 
                        WHERE id_kategori = {$data_kategori['id_kategori']}")->num_rows(); 
                        if($get_kategori_baseID>0){
                            $get_photo_url = $this->db->query("
                            SELECT photo_url
                            FROM kategori
                            WHERE id_kategori = {$data_kategori['id_kategori']}")->result();
            
                            if(!empty($get_photo_url)){
                                $photo_nama_file = basename($get_photo_url[0]->photo_url);
                                $photo_lokasi_file = realpath(FCPATH . $this->folder_upload . $photo_nama_file);
            
                            if(file_exists($photo_lokasi_file)){
                                unlink($photo_lokasi_file);
                            }
                            $this->db->query("
                                DELETE FROM kategori 
                                WHERE id_kategori = {$data_kategori['id_kategori']}");
                                $this->response(
                                    array(
                                        "status" =>"success", 
                                        "message" => "Data ID = " .$data_kategori['id_kategori']. "berhasil dihapus"
                                    )
                                    );
                            }
                        }else{
                            $this->response(
                                array(
                                    "status" => "failed",
                                    "message" => "ID kategori tidak ditemukan"
                                )
                                );
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
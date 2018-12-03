<?php

defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

    class berita extends REST_Controller{
    
        private $folder_upload='uploads/';
        //show data berita
        function user_get(){
            

            $get_transaksi = $this->db->query("
            SELECT
               * from berita")->result();
                $this->response(
                    array(
                        "status"=> "success", 
                        "result" =>$get_transaksi
                    )
                    );
                     
        }
        // function user_get($data_berita){
        //     $get_transaksi = $this->db->query("SELECT br.id_berita, br.id_sumber, 
        //  br.id_kategori, br.judul, br.isi, br.gambar FROM sumber,berita br,
        //   kategori WHERE br.id_berita=kategori.id_kategori AND 
        //   br.id_sumber= sumber.id_sumber") -> result(); 

        //     $this->response(array("status"=>"success","result"=> $get_transaksi));
            
        // }
        function user_insert($data_berita){
            if(empty($data_berita['id_berita'])){
                $this->response(array('status' =>'fail',"message"=>"id_berita kosong"));
            }else{
                    if(empty($data_berita['id_sumber'])){
                        $this->response(array('status'=>'fail',"message"=>"id_sumber kosong"));
                    }else if(empty($data_berita['id_kategori'])){
                        $this->response(array('status'=>'fail',"message"=>"id_kategori kosong"));
                    }else if(empty($data_berita['judul'])){
                        $this->response(array('status'=>'fail',"message"=>"judul kosong"));
                    }else if(empty($data_berita['isi'])){
                        $this->response(array('status'=>'fail',"message"=>"isi kosong"));
                    }else{
                        
                        $data_berita['gambar'] = $this->uploadPhoto();
                        
                                    $do_insert = $this->db->insert('berita', $data_berita);
                                   
                                    if ($do_insert){
                                        $this->response(
                                            array(
                                                "status" => "success",
                                                "result" => array($data_berita),
                                                "message" => $do_insert
                                            )
                                        );
                                    }
                        $getIdSumber = $this->db->query ("SELECT id_sumber from sumber WHERE id_sumber='" .$data_berita['id_sumber']."'")->result();
                        $getIdKategori = $this->db->query("SELECT id_kategori from kategori WHERE id_kategori='".$data_berita['id_kategori']."'")->result();
                        $message="";
                        if(empty($getIdSumber)) $message.="id_sumber tidak ada/salah";
                        if(empty($getIdKategori)){
                            if(empty($message)){
                                $message.="id_kategori tidak ada/salah";
                            }
                            else{
                                $message.="dan id_kategori tidak ada/salah";
                            }
                        }
                        if(empty($message)){
                            $insert = $this->db->insert('berita',$data_berita);
                            if($insert){
                                $this->response(array('status'=>'success','result'=>$data_berita,"message"=>$insert));
                            }
                        }else{
                            $this->response(array('status'=>'fail',"message"=>"id_kategori tidak dalam database"));
                        }
                    }
                }
        }
        function user_update($data_berita){
                $getId= $this->db->query("SELECT id_berita from berita WHERE id_berita='".$data_berita['id_berita']."'")->result();
                if(empty($getId)){
                    $this->response(array('status'=>'fail',"message"=>"id_berita tidak ada/salah"));
                }else{
                    if(empty($data_berita['id_kategori'])){
                        $this->response(array('status'=>'fail',"message"=>"id_kategori kosong"));
                    }else if(empty($data_berita['judul'])){
                        $this->response(array('status','fail',"message"=>"judul kosong"));
                    }else if(empty($data_berita['isi'])){
                        $this->response(array('status'=>'fail',"message"=>"berita kosong"));
                    }else if(empty($data_berita['gambar'])){
                        $data_berita['gambar'] = $this->uploadPhoto();
                        if($data_berita['gambar']){
                            $update = $this->db->query(" 
                            UPDATE berita SET 
                          id_berita ='{$data_berita['id_berita']}',
                            id_sumber = '{$data_berita['id_sumber']}',
                            judul = '{$data_berita['judul']}',
                            isi = '{$data_berita['isi']}',
                            gambar = '{$data_berita['gambar']}'
                            WHERE id_berita = '{$data_berita['id_berita']}'");
                        }else{
                            $update = $this->db->query("
                            UPDATE berita
                            SET
                                id_berita ='{$data_berita['id_berita']}',
                                id_sumber = '{$data_berita['id_sumber']}',
                                judul ='{$data_berita['judul']}',
                               isi = '{$data_berita['isi']}'
                            WHERE id_berita = {$data_berita['id_berita']}"
                        );
                        }
                    
                        $getIdSumber= $this->db->query("SELECT id_sumber FROM sumber WHERE id_sumber='".$data_berita['id_sumber']."'")->result();
                        $getIdKategori= $this->db->query("SELECT id_kategori FROM kategori WHERE id_kategori='".$data_berita['id_kategori']."'")->result();
                        $message="";
                        if(empty($getIdSumber)) $message.="id_sumber tidak ada/salah";
                        if(empty($getIdKategori)){
                            if(empty($message)){
                                $message.="id_kategori tidak ada/salah";
                            }
                            else{
                                $message.="dan id_sumber tidak ada/salah";
                                
                            }
                        }
                        if(empty($message)){
                            $this->db->where('id_berita', $data_berita['id_berita']);
                            $update= $this->db->update ('berita',$data_berita);
                            if($update){
                                $this->response(array('status'=>'success','result'=>$data_berita, "message"=>$update));
                            }
                        }else{
                            $this->response(array('status'=>'fail',"message"=>$message));
                        }
                }
            }
        }
    
    function user_delete($data_berita){
       
        $getId= $this->db->query("SELECT id_berita from berita WHERE id_berita='".$data_berita['id_berita']."'")->result();
        if(empty($getId)){
            $this->response(array('status'=>'fail',"message"=>"id_berita tidak ada/salah"));
        }else{
                $this->db->where('id_berita',$data_berita['id_berita']);
                $delete = $this->db->delete('berita');
                if($this->db->affected_rows()){
                    $this->response(array('status'=>'success','message'=>"Berhasil delete dengan id_berita = ".$data_berita['id_berita']));
                }else{
                    $this->response(array('status'=>'fail','message' =>"id_berita tidak dalam database"));
                }
            }
        }
        function user_post(){
            $action = $this->post('action');
            $data_berita = array(
                'id_berita' =>$this->post('id_berita'),
                'id_sumber' => $this->post('id_sumber'),
                'id_kategori' => $this->post('id_kategori'),
                'judul' =>$this->post('judul'),
                'isi' =>$this->post('isi'),
                'gambar' => $this->post('gambar')
            );
            // var_dump($data_berita);
            // die();
            switch ($action){
                case 'insert';
                $this->user_insert($data_berita);
                break;
    
                case 'update': 
                $this->user_update($data_berita);
                break; 
    
                case 'delete' : 
                $this->user_delete($data_berita);
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
        function uploadPhoto() {
            
                    // Apakah user upload gambar?
                    if ( isset($_FILES['gambar']) && $_FILES['gambar']['size'] > 0 ){
            
                        // Foto disimpan di android-api/uploads
                        $config['upload_path'] = realpath(FCPATH . $this->folder_upload);
                        $config['allowed_types'] = 'jpg|png';
            
                        // Load library upload & helper
                        $this->load->library('upload', $config);
                        $this->load->helper('url');
            
                        // Apakah file berhasil diupload?
                        if ( $this->upload->do_upload('gambar')) {
            
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
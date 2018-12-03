<?php

defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';


    class Sumber extends REST_Controller{
        private $folder_upload='uploads/';

        function all_get(){
            $get_sumber = $this->db->query("
            SELECT
                id_sumber, 
                nama_sumber,
                pj_sumber 
                FROM sumber")->result();
                $this->response(
                    array(
                        "status"=> "success", 
                        "result" =>$get_sumber
                    )
                    );
        }
        function all_post(){
            $action = $this->post('action');
            $data_sumber = array(
                'id_sumber' =>$this->post('id_sumber'),
                'nama_sumber' => $this->post('nama_sumber'),
                'pj_sumber' => $this->post('pj_sumber')
            );
            switch ($action){
                case 'insert';
                $this->insertSumber($data_sumber);
                break;
    
                case 'update': 
                $this->updateSumber($data_sumber);
                break; 
    
                case 'delete' : 
                $this->deleteSumber($data_sumber);
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
        function insertSumber($data_sumber){
            
                    // Cek validasi
                    if (empty($data_sumber['id_sumber'])){
                        $this->response(
                            array(
                                "status" => "failed",
                                "message" => "Sumber  harus diisi"
                            )
                        );
                    }
                        else{
                            $do_insert = $this->db->insert('sumber', $data_sumber);
                            if ($do_insert){
                                $this->response(
                                    array(
                                        "status" => "success",
                                        "result" => array($data_sumber),
                                        "message" => $do_insert
                                    )
                                );
                            }
                        }
                }
                function updateSumber($data_sumber){
                    $getId= $this->db->query("SELECT id_sumber from sumber WHERE id_sumber='".$data_sumber['id_sumber']."'")->result();
                    if(empty($getId)){
                        $this->response(array('status'=>'fail',"message"=>"id_sumber tidak ada/salah"));
                    }else{
                    if(empty($data_sumber['id_sumber'])){
                        $this->response(array('status'=>'fail',"message"=>"id_sumber kosong"));
                    }else{
                        $getId= $this->db->query("SELECT id_sumber from sumber WHERE id_sumber='".$data_sumber['id_sumber']."'")->result();
                        if(empty($getId)){
                            $this->response(array('status'=>'fail',"message"=>"id_sumber tidak ada/salah"));
                        }else{
                            if(empty($data_sumber['id_sumber'])){
                                $this->response(array('status'=>'fail',"message"=>"id_sumber kosong"));
                            }else if(empty($data_sumber['nama_sumber'])){
                                $this->response(array('status'=>'fail',"message"=>"nama_sumber kosong"));
                            }else if(empty($data_sumber['pj_sumber'])){
                                $this->response(array('status','fail',"message"=>'"pj_sumber kosong'));
                            }
                            else{
                                if(empty($message)){
                                    $this->db->where('id_sumber', $data_sumber['id_sumber']);
                                    $update= $this->db->update ('sumber',$data_sumber);
                                    if($update){
                                        $this->response(array('status'=>'success','result'=>$data_sumber, "message"=>$update));
                                    }
                                }else{
                                    $this->response(array('status'=>'fail',"message"=>$message));
                                }
                            }
                        }
                    }            
        } 
    }
                function deleteSumber($data_sumber){
                    if (empty($data_sumber['id_sumber'])){
                        $this->response(
                            array(
                                "status" =>"failed",
                                "message" =>"ID sumber harus diisi"
                            )
                            );
                    }else{
                        $get_sumber_baseID = $this->db->query("
                        SELECT 1
                        FROM sumber 
                        WHERE id_sumber = {$data_sumber['id_sumber']}")->num_rows(); 
                        if($get_sumber_baseID>0){
                            $this->db->query("
                            DELETE FROM sumber 
                            WHERE id_sumber = {$data_sumber['id_sumber']}");
                            $this->response(
                                array(
                                    "status" =>"success", 
                                    "message" => "Data ID = " .$data_sumber['id_sumber']. "berhasil dihapus"
                                )
                                );
                            }
                        else{
                            $this->response(
                                array(
                                    "status" => "failed",
                                    "message" => "ID sumber tidak ditemukan"
                                )
                                );
                        }
                    }
                }

    }
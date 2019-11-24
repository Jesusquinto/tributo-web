/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;


import co.tributo.cliente.model.FrUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */
public interface FrUsuarioRepository extends JpaRepository<FrUsuario, Integer>{
    
    @Query("SELECT f FROM FrUsuario f WHERE f.email = :email")
    FrUsuario getFrUsuarioByEmail(@Param("email") String email);
    
    @Modifying
    @Query(value="DELETE FROM fr_usuario where verified_account=1 and EXTRACT(HOUR FROM TIMEDIFF(now(), expiration_code)) >= 24", nativeQuery = true)
    void checkVerifiedAccount();
    
    @Query("SELECT f FROM FrUsuario f WHERE f.verificaionCode = :verificaionCode")
    FrUsuario getFrUsuarioByVerificaionCode(@Param("verificaionCode") String verificaionCode);

    
    }

   
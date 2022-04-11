import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import User from '../../components/User';
import api from '../../services/api';
import './styles.css';


// Crud de Cadastro
// Lista de participantes com suas opções
export default function Home() {
  const [usersList, setUsersList] = useState([]);
  const getUsers = async () => {
    try {
      const response = await api.get("/users");
      const {users} = response.data._embedded
      console.log(users);
      setUsersList(users)
    } catch (error) {
      
    }}
  
  useEffect(() => {
    getUsers();
    // return () => {
    //   cleanup
    // }
  }, []);

    return (
      <div class="home">
        <div className="buttonWraper buttonWrapper--home">
          <Link className="button" to="new/user">
            Criar colaborador
          </Link>
          <Link className="button" to="new/food">
            Criar comida
          </Link>
        </div>
        {usersList.map((item) => (
          <User item={item} />
        ))}
      </div>
    );
}

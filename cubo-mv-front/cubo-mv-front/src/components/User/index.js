import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import axios from 'axios';
import { Link } from 'react-router-dom';

// import { Container } from './styles';
import './styles.css'
function User({item}) {
    console.log("USER", item)
    const links = item && item._links;
    const id = item && item._links.self.href.split("/").pop();
    const foodsLink = links && links.foods;
    console.log("FOOD", links, foodsLink)
    const name = item && `${item.firstName} ${item.lastName}`
    const cpf = item && item.cpf;
    const [foods, setFoods] = useState([])

    const getFoods = async () => {
        if (id) {
          try {
            //   const response = await api.get(foodsLink);
            const response = await api.get(`users/${id}/foods`);
            const { foods } = response.data._embedded;
            setFoods(foods);
            console.log("BAIXOU AS FOODS", foods);
          } catch (error) {
            console.log(error);
          }
        } else {
        }
}
      
      useEffect(() => {
        getFoods();
      }, [item]);
  return (
    <Link to={`new/user/${id}`} class="block">
      <div><b>CPF:</b> {cpf}</div>
      <div><b>NOME:</b> {name}</div>
      <div>Lista de comidas:</div>
      {foods.map((food) => (
        <div>- {food.name}</div>
      ))}
    </Link>
  );
}

export default User;
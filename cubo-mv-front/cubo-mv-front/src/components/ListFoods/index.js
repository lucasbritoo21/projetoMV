import axios from 'axios';
import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import Food from './Food';
import './styles.css'

function ListFoods({userId, cpf}) {
  const [foodsList, setFoodsList] = useState([]);
  const getFoods = async () => {
    try {
      const response = await api.get("/foods");
      const { foods } = response.data._embedded;
      console.log(foods);
      setFoodsList(foods);
      console.log(response);
    } catch (error) {}
  };
  useEffect(() => {
    getFoods();
  }, [userId]);

  return (
    <div>
      <div className="food__h1">Escolher comida</div>
    <div className="foodList">
      {foodsList.map((food) => (
        <Food userId={userId} cpf={cpf} item={food} />
      ))}
    </div>
    </div>
  );
}

export default ListFoods;
import axios from 'axios';


export const URL = 'http://localhost:8080'


const api = axios.create({
	baseURL: URL,
	headers: {
		Accept: 'application/json',
		'Content-Type': 'application/json',
	}
});
// export const putRelation = 


export default api;

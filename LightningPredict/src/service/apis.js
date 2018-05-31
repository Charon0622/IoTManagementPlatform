import fetch from './fetch'
import { baseUrl, dataBaseUrl } from '../../config/index'

var login = (username, password) => fetch('POST', baseUrl, 'login/', {username: username, password: password})

var logout = () => fetch('GET', baseUrl, 'logout/', {})

var isLogin = (username) => fetch('GET', baseUrl, 'is_login/?username=' + username, {})

var register = (username, password) => fetch('POST', baseUrl, 'register/', {username: username, password: password})

var terminal = () => fetch('GET', dataBaseUrl, 'terminal/getall/', {})

var terminal2 = () => fetch('GET', dataBaseUrl, 'terminal2/getall/', {})

export {
  login,
  logout,
  isLogin,
  register,
  terminal,
  terminal2
}

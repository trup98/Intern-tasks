import React, {Component} from 'react';

class UserGreeting extends Component {

  state = {
    isLoggedIn: true
  }

  render() {

    // short circuit conditional rendering
    return this.state.isLoggedIn && <div>Welcome User</div>

    // ternary conditional operator
    // return (
    //   this.state.isLoggedIn ?
    //     <div>Welcome User</div> :
    //     <div>Welcome Guest</div>
    // )

    // element variable
    // let message;
    // if (this.state.isLoggedIn) {
    //   message = <div>Welcome User</div>
    // } else {
    //   message = <div>Welcome Guest</div>
    // }
    // return <div>{message}</div>

    // if else conditional
    // if (this.state.isLoggedIn){
    //   return <div>Welcome User</div>
    // }else {
    //   return <div>Welcome Guest</div>
    // }

    // return (
    //   <div>
    //     <div>Hello User</div>
    //     <div>Hello Guest</div>
    //   </div>
    // );
  }
}

export default UserGreeting;

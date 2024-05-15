import {Navbar} from "./Navbar";

export const Base = ({children}) => {
  return (
    <div>
      <Navbar/>
      <div style={{display:'flex'}}>
        <main>{children}</main>
      </div>
    </div>
  )
}

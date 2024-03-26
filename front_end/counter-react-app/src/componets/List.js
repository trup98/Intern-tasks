import BlogList from "./BlogList";
import useFetch from "./useFetch";

const List = () => {

  /*[
    {title: "first react application", body: "lorem ...", author: "Ts", id: 1},
    {title: "Learning react application", body: "lorem ...", author: "Gs", id: 2},
    {title: "first react application", body: "lorem ...", author: "Ts", id: 3},
  ]*/

  /*  const handleDelete = (id) => {
      const temBlogs = blogs.filter(blog => blog.id !== id);
      setBlogs(temBlogs);
    }*/

  const {data: blogs, isLoading, error} = useFetch('http://localhost:8000/blogs');


  return (
    <div className="list">
      {error && <div>{error}</div>}
      {isLoading && <div>Loading ...</div>}
      {blogs && <BlogList blogs={blogs} title="All blogs"/>}


      {/*condition templating*/}
      {/*using conditional bcz in first state the value become null */}
      {/*logical first evaluates the left first if its false it never bother right one */}
      {/*handleDelete={handleDelete}*/}
      {/*<BlogList blogs={blogs.filter((blog) => blog.author === "Gs")} title="Gs blog" handleDelete={handleDelete}/>*/}
    </div>
  );
}

export default List;

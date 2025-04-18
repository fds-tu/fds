import type { Route } from "./+types/home";

export function meta({}: Route.MetaArgs) {
  return [{ title: "FDS" }, { name: "description", content: "FDS" }];
}

export default function Home({ loaderData }: Route.ComponentProps) {
  return <h1>FDS</h1>;
}

<?php

namespace App\Controller;

use App\Entity\Publication;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class DashboardController extends AbstractController
{
    /**
     * @Route("/admin", name="app_admin")
     */
    public function index(): Response
    {
        return $this->render('dashboard/index.html.twig');
    }

    /**
     * @Route("/admin/user", name="afficheuser")
     */
    public function affiche(): Response
    {
        //$rep = $this->getDoctrine()->getRepository(User::class);
        //$users = $rep->findAll();
        return $this->render('dashboard/afficheuser.html.twig');
    }

}